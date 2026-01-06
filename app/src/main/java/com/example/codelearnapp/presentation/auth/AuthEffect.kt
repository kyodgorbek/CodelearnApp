package com.example.codelearnapp.presentation.auth

import com.example.codelearnapp.presentation.mvi.UiEffect

sealed class AuthEffect : UiEffect {
    object NavigateToHome : AuthEffect()
    data class ShowError(val message: String) : AuthEffect()
    data class ShowSuccess(val message: String) : AuthEffect()
}