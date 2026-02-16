package com.yodgorbek.codelearnapp.presentation.auth

import com.yodgorbek.codelearnapp.presentation.mvi.UiEffect

sealed class AuthEffect : UiEffect {
    object NavigateToHome : AuthEffect()
    data class ShowError(val message: String) : AuthEffect()
    data class ShowSuccess(val message: String) : AuthEffect()
}
