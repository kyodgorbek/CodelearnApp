package com.example.codelearnapp.presentation.lesson

import androidx.lifecycle.viewModelScope
import com.example.codelearnapp.domain.usecase.CompleteLessonUseCase
import com.example.codelearnapp.domain.usecase.GetLessonByIdUseCase
import com.example.codelearnapp.presentation.mvi.MviViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LessonViewModel(
    private val getLessonByIdUseCase: GetLessonByIdUseCase,
    private val completeLessonUseCase: CompleteLessonUseCase
) : MviViewModel<LessonIntent, LessonState, LessonEffect>() {
    
    override fun createInitialState(): LessonState = LessonState()
    
    override fun handleIntent(intent: LessonIntent) {
        when (intent) {
            is LessonIntent.LoadLesson -> loadLesson(intent.lessonId)
            is LessonIntent.AnswerQuiz -> handleQuizAnswer(intent.answerIndex)
            is LessonIntent.CompleteLesson -> completeLesson()
            is LessonIntent.BackPressed -> sendEffect(LessonEffect.NavigateBack)
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
            sendEffect(LessonEffect.ShowCompletionCelebration)
            sendEffect(LessonEffect.NavigateBack)
        }
    }
}