package com.yodgorbek.codelearnapp.presentation.lesson

import com.yodgorbek.codelearnapp.presentation.mvi.UiIntent

sealed class LessonIntent : UiIntent {
    data class LoadLesson(val lessonId: String) : LessonIntent()
    data class AnswerQuiz(val answerIndex: Int) : LessonIntent()
    object CompleteLesson : LessonIntent()
    object BackPressed : LessonIntent()
    object DismissCelebration : LessonIntent()
    data class UpdateCode(val code: String) : LessonIntent()
    data class UpdateCodeInput(val input: String) : LessonIntent()
    object RunCode : LessonIntent()
}
