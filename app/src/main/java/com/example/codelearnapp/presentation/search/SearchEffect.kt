package com.example.codelearnapp.presentation.search

import com.example.codelearnapp.presentation.mvi.UiEffect

sealed class SearchEffect : UiEffect {
    data class NavigateToLesson(val lessonId: String) : SearchEffect()
}