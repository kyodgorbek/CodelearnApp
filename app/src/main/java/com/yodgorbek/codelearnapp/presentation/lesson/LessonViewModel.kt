package com.yodgorbek.codelearnapp.presentation.lesson

import androidx.lifecycle.viewModelScope
import com.yodgorbek.codelearnapp.domain.usecase.CompleteLessonUseCase
import com.yodgorbek.codelearnapp.domain.usecase.GetLessonByIdUseCase
import com.yodgorbek.codelearnapp.domain.usecase.GetUserProgressUseCase
import com.yodgorbek.codelearnapp.presentation.mvi.MviViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

import com.yodgorbek.codelearnapp.data.local.PreferencesManager
import com.yodgorbek.codelearnapp.domain.codeexecution.CodeExecutor
import com.yodgorbek.codelearnapp.domain.codeexecution.CodeExecutionResult
import com.yodgorbek.codelearnapp.domain.model.CourseCategory
import com.yodgorbek.codelearnapp.presentation.tutor.analysis.RuleBasedErrorDetector
import kotlinx.coroutines.flow.combine

class LessonViewModel(
    private val getLessonByIdUseCase: GetLessonByIdUseCase,
    private val completeLessonUseCase: CompleteLessonUseCase,
    private val getUserProgressUseCase: GetUserProgressUseCase,
    private val preferencesManager: PreferencesManager,
    private val codeExecutor: CodeExecutor
) : MviViewModel<LessonIntent, LessonState, LessonEffect>(LessonState()) {

    private val errorDetector = RuleBasedErrorDetector()

    override fun handleIntent(intent: LessonIntent) {
        when (intent) {
            is LessonIntent.LoadLesson -> loadLesson(intent.lessonId)
            is LessonIntent.AnswerQuiz -> handleQuizAnswer(intent.answerIndex)
            is LessonIntent.CompleteLesson -> completeLesson()
            is LessonIntent.BackPressed -> sendEffect(LessonEffect.NavigateBack)
            is LessonIntent.DismissCelebration -> dismissCelebration()
            is LessonIntent.UpdateCode -> {
                val error = errorDetector.detect(intent.code)
                setState { copy(currentCode = intent.code, codeError = error) }
            }
            is LessonIntent.RunCode -> runCode()
        }
    }

    private fun loadLesson(lessonId: String) {
        viewModelScope.launch {
            combine(
                getLessonByIdUseCase(lessonId),
                preferencesManager.autoPlayVideo
            ) { lesson, autoPlay ->
                Pair(lesson, autoPlay)
            }
            .catch { e ->
                setState { copy(isLoading = false, error = e.message) }
            }
            .collect { (lesson, autoPlay) ->
                setState {
                    copy(
                        isLoading = false,
                        lesson = lesson,
                        autoPlayVideo = autoPlay,
                        currentCode = lesson?.codeExample ?: "",
                        error = null
                    )
                }
            }
        }
    }

    private fun runCode() {
        val lesson = state.value.lesson ?: return
        val code = state.value.currentCode
        val input = ""

        setState { copy(isExecuting = true, executionOutput = "") }

        viewModelScope.launch {
            val isCompose = code.contains("@Composable") ||
                code.contains("Modifier") ||
                code.contains("setContent") ||
                code.contains("NavHost")

            if (isCompose) {
                setState {
                    copy(
                        isExecuting = false,
                        hasExecuted = true,
                        executionOutput = "This lesson contains UI code and cannot run in the console."
                    )
                }
                return@launch
            }

            // Use explicit language from lesson, fallback to reliable detection if needed
            val language = lesson.language.lowercase()
            
            val result = when (language) {
                "python", "python3" -> codeExecutor.executePythonCode(code, input)
                "kotlin" -> codeExecutor.executeKotlinCode(code, input)
                "javascript", "js", "nodejs" -> codeExecutor.executeJavaScriptCode(code, input)
                "java" -> codeExecutor.executeJavaCode(code, input)
                "sql", "sqlite" -> codeExecutor.executeSqlCode(code, input)
                else -> CodeExecutionResult.Error("Unsupported language: $language")
            }

            when (result) {
                is CodeExecutionResult.Success -> {
                    setState { copy(isExecuting = false, hasExecuted = true, executionOutput = result.output) }
                }
                is CodeExecutionResult.Error -> {
                    setState { copy(isExecuting = false, hasExecuted = true, executionOutput = "Error: ${result.message}") }
                }
            }
        }
    }

    private fun handleQuizAnswer(answerIndex: Int) {
        setState {
            copy(
                selectedAnswer = answerIndex,
                showQuizResult = true
            )
        }
    }

    private fun completeLesson() {
        val lesson = state.value.lesson ?: return

        viewModelScope.launch {
            completeLessonUseCase(lesson.id)

            // Check current progress for milestones
            val progress = getUserProgressUseCase().firstOrNull()
            val lessonsCompleted = progress?.lessonsCompleted ?: 0

            val milestone = when {
                lessonsCompleted > 0 && lessonsCompleted % 30 == 0 -> "$lessonsCompleted Lessons Completed! You're achieving mastery!"
                lessonsCompleted == 1 -> "Your first lesson! You're on your way!"
                lessonsCompleted % 10 == 0 -> "Wow! $lessonsCompleted lessons completed! You're a rockstar!"
                else -> null
            }

            setState {
                copy(
                    showCelebration = true,
                    isMajorMilestone = lessonsCompleted > 0 && lessonsCompleted % 30 == 0,
                    milestoneReached = milestone
                )
            }
            sendEffect(LessonEffect.ShowCompletionCelebration)
        }
    }

    private fun dismissCelebration() {
        setState { copy(showCelebration = false) }
        sendEffect(LessonEffect.NavigateBack)
    }
}
