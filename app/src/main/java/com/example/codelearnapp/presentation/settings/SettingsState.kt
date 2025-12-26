package com.example.codelearnapp.presentation.settings

import com.example.codelearnapp.presentation.mvi.UiState

data class SettingsState(
    val isDarkMode: Boolean = false,
    val isOfflineMode: Boolean = false,
    val notificationsEnabled: Boolean = true,
    val autoPlayVideo: Boolean = true,
    val selectedLanguage: String = "en",
    val dailyGoal: Int = 3,
    val isSignedIn: Boolean = false,
    val userEmail: String? = null,
    val showLanguageDialog: Boolean = false,
    val showDailyGoalDialog: Boolean = false
) : UiState