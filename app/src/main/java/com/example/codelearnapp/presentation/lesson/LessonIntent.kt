package com.example.codelearnapp.presentation.lesson

import com.example.codelearnapp.domain.model.Lesson
import com.example.codelearnapp.presentation.mvi.UiEffect
import com.example.codelearnapp.presentation.mvi.UiIntent
import com.example.codelearnapp.presentation.mvi.UiState

sealed class LessonIntent : UiIntent {
    data class LoadLesson(val lessonId: String) : LessonIntent()
    data class AnswerQuiz(val answerIndex: Int) : LessonIntent()
    object CompleteLesson : LessonIntent()
    object BackPressed : LessonIntent()
}