package com.example.codelearnapp.presentation.settings

import androidx.lifecycle.viewModelScope
import com.example.codelearnapp.data.local.PreferencesManager
import com.example.codelearnapp.data.remote.FirebaseAuthRepository
import com.example.codelearnapp.data.sync.SyncManager
import com.example.codelearnapp.presentation.mvi.*
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

sealed class SettingsIntent : UiIntent {
    object LoadSettings : SettingsIntent()
    data class ToggleDarkMode(val enabled: Boolean) : SettingsIntent()
    data class ToggleOfflineMode(val enabled: Boolean) : SettingsIntent()
    data class ToggleNotifications(val enabled: Boolean) : SettingsIntent()
    data class ToggleAutoPlayVideo(val enabled: Boolean) : SettingsIntent()
    data class SelectLanguage(val language: String) : SettingsIntent()
    data class SetDailyGoal(val goal: Int) : SettingsIntent()
    object ShowLanguageDialog : SettingsIntent()
    object DismissLanguageDialog : SettingsIntent()
    object ShowDailyGoalDialog : SettingsIntent()
    object DismissDailyGoalDialog : SettingsIntent()
    object SyncData : SettingsIntent()
    object SignOut : SettingsIntent()
    object NavigateToSignIn : SettingsIntent()
    object NavigateToProfile : SettingsIntent()
    object ShowPrivacyPolicy : SettingsIntent()
    object ShowTerms : SettingsIntent()
}