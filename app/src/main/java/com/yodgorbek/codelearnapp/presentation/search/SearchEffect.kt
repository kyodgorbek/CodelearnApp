package com.yodgorbek.codelearnapp.presentation.search

import com.yodgorbek.codelearnapp.presentation.mvi.UiEffect

sealed class SearchEffect : UiEffect {
    data class NavigateToLesson(val lessonId: String) : SearchEffect()
}
