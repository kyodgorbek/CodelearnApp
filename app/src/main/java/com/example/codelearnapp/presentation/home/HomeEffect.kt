package com.example.codelearnapp.presentation.home

import com.example.codelearnapp.presentation.mvi.UiEffect

sealed class HomeEffect : UiEffect {
    data class NavigateToCourse(val courseId: String) : HomeEffect()
    data class ShowError(val message: String) : HomeEffect()
}