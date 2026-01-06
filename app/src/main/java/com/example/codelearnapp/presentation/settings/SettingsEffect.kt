package com.example.codelearnapp.presentation.settings

import com.example.codelearnapp.presentation.mvi.UiEffect

sealed class SettingsEffect : UiEffect {
    object NavigateToSignIn : SettingsEffect()
    object NavigateToProfile : SettingsEffect()
    object NavigateToPrivacyPolicy : SettingsEffect()
    object NavigateToTerms : SettingsEffect()
    data class ShowMessage(val message: String) : SettingsEffect()
}