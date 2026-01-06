package com.example.codelearnapp.presentation.lesson

import androidx.lifecycle.viewModelScope
import com.example.codelearnapp.domain.usecase.CompleteLessonUseCase
import com.example.codelearnapp.domain.usecase.GetLessonByIdUseCase
import com.example.codelearnapp.domain.usecase.GetUserProgressUseCase
import com.example.codelearnapp.presentation.mvi.MviViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

import com.example.codelearnapp.data.local.PreferencesManager
import com.example.codelearnapp.domain.codeexecution.CodeExecutor
import com.example.codelearnapp.domain.codeexecution.CodeExecutionResult
import com.example.codelearnapp.domain.model.CourseCategory
import kotlinx.coroutines.flow.combine

class LessonViewModel(
    private val getLessonByIdUseCase: GetLessonByIdUseCase,
    private val completeLessonUseCase: CompleteLessonUseCase,
    private val getUserProgressUseCase: GetUserProgressUseCase,
    private val preferencesManager: PreferencesManager,
    private val codeExecutor: CodeExecutor
) : MviViewModel<LessonIntent, LessonState, LessonEffect>(LessonState()) {
    
    override fun handleIntent(intent: LessonIntent) {
        when (intent) {
            is LessonIntent.LoadLesson -> loadLesson(intent.lessonId)
            is LessonIntent.AnswerQuiz -> handleQuizAnswer(intent.answerIndex)
            is LessonIntent.CompleteLesson -> completeLesson()
            is LessonIntent.BackPressed -> sendEffect(LessonEffect.NavigateBack)
            is LessonIntent.DismissCelebration -> dismissCelebration()
            is LessonIntent.UpdateCode -> setState { copy(currentCode = intent.code) }
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
        
        setState { copy(isExecuting = true, executionOutput = "") }
        
        viewModelScope.launch {
            val courseId = lesson.courseId
            // Determine language based on course ID or category (simplification for simulation)
            val result = when {
                courseId.contains("python") || courseId.contains("data-science") -> codeExecutor.executePythonCode(code)
                courseId.contains("kotlin") -> codeExecutor.executeKotlinCode(code)
                courseId.contains("web-dev") || courseId.contains("js") -> codeExecutor.executeJavaScriptCode(code)
                courseId.contains("java") -> codeExecutor.executeJavaCode(code)
                courseId.contains("sql") || courseId.contains("database") -> codeExecutor.executeSqlCode(code)
                else -> CodeExecutionResult.Success("Code executed successfully")
            }
            
            when (result) {
                is CodeExecutionResult.Success -> {
                    setState { copy(isExecuting = false, executionOutput = result.output) }
                }
                is CodeExecutionResult.Error -> {
                    setState { copy(isExecuting = false, executionOutput = "Error: ${result.message}") }
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
