package com.yodgorbek.codelearnapp.presentation.lesson

import com.yodgorbek.codelearnapp.presentation.mvi.UiEffect

sealed class LessonEffect : UiEffect {
    object NavigateBack : LessonEffect()
    object ShowCompletionCelebration : LessonEffect()
    data class ShowError(val message: String) : LessonEffect()
}
