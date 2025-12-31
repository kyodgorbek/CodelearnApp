package com.example.codelearnapp.presentation.lesson

import androidx.lifecycle.viewModelScope
import com.example.codelearnapp.domain.usecase.CompleteLessonUseCase
import com.example.codelearnapp.domain.usecase.GetLessonByIdUseCase
import com.example.codelearnapp.domain.usecase.GetUserProgressUseCase
import com.example.codelearnapp.presentation.mvi.MviViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class LessonViewModel(
    private val getLessonByIdUseCase: GetLessonByIdUseCase,
    private val completeLessonUseCase: CompleteLessonUseCase,
    private val getUserProgressUseCase: GetUserProgressUseCase
) : MviViewModel<LessonIntent, LessonState, LessonEffect>(LessonState()) {
    
    override fun handleIntent(intent: LessonIntent) {
        when (intent) {
            is LessonIntent.LoadLesson -> loadLesson(intent.lessonId)
            is LessonIntent.AnswerQuiz -> handleQuizAnswer(intent.answerIndex)
            is LessonIntent.CompleteLesson -> completeLesson()
            is LessonIntent.BackPressed -> sendEffect(LessonEffect.NavigateBack)
            is LessonIntent.DismissCelebration -> dismissCelebration()
        }
    }
    
    private fun loadLesson(lessonId: String) {
        viewModelScope.launch {
            getLessonByIdUseCase(lessonId)
                .catch { e ->
                    setState { copy(isLoading = false, error = e.message) }
                }
                .collect { lesson ->
                    setState {
                        copy(
                            isLoading = false,
                            lesson = lesson,
                            error = null
                        )
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
