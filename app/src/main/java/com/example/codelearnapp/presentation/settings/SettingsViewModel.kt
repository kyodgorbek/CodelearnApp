package com.example.codelearnapp.presentation.settings

import androidx.lifecycle.viewModelScope
import com.example.codelearnapp.data.local.PreferencesManager
import com.example.codelearnapp.data.remote.FirebaseAuthRepository
import com.example.codelearnapp.data.sync.SyncManager
import com.example.codelearnapp.presentation.mvi.MviViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val preferencesManager: PreferencesManager,
    private val authRepository: FirebaseAuthRepository,
    private val syncManager: SyncManager,
    private val firebaseAuth: com.google.firebase.auth.FirebaseAuth
) : MviViewModel<SettingsIntent, SettingsState, SettingsEffect>() {
    
    override fun createInitialState(): SettingsState = SettingsState()
    
    init {
        sendIntent(SettingsIntent.LoadSettings)
    }
    
    override fun handleIntent(intent: SettingsIntent) {
        when (intent) {
            is SettingsIntent.LoadSettings -> loadSettings()
            is SettingsIntent.ToggleDarkMode -> toggleDarkMode(intent.enabled)
            is SettingsIntent.ToggleOfflineMode -> toggleOfflineMode(intent.enabled)
            is SettingsIntent.ToggleNotifications -> toggleNotifications(intent.enabled)
            is SettingsIntent.ToggleAutoPlayVideo -> toggleAutoPlayVideo(intent.enabled)
            is SettingsIntent.SelectLanguage -> selectLanguage(intent.language)
            is SettingsIntent.SetDailyGoal -> setDailyGoal(intent.goal)
            is SettingsIntent.ShowLanguageDialog -> setState { copy(showLanguageDialog = true) }
            is SettingsIntent.DismissLanguageDialog -> setState { copy(showLanguageDialog = false) }
            is SettingsIntent.ShowDailyGoalDialog -> setState { copy(showDailyGoalDialog = true) }
            is SettingsIntent.DismissDailyGoalDialog -> setState { copy(showDailyGoalDialog = false) }
            is SettingsIntent.SyncData -> syncData()
            is SettingsIntent.SignOut -> signOut()
            is SettingsIntent.NavigateToSignIn -> sendEffect(SettingsEffect.NavigateToSignIn)
            is SettingsIntent.NavigateToProfile -> sendEffect(SettingsEffect.NavigateToProfile)
            is SettingsIntent.ShowPrivacyPolicy -> sendEffect(SettingsEffect.NavigateToPrivacyPolicy)
            is SettingsIntent.ShowTerms -> sendEffect(SettingsEffect.NavigateToTerms)
        }
    }
    
    private fun loadSettings() {
        viewModelScope.launch {
            combine(
                preferencesManager.isDarkMode,
                preferencesManager.isOfflineMode,
                preferencesManager.notificationEnabled,
                preferencesManager.autoPlayVideo,
                preferencesManager.selectedLanguage,
                preferencesManager.dailyGoal
            ) { darkMode, offlineMode, notifications, autoPlay, language, goal ->
                setState {
                    copy(
                        isDarkMode = darkMode,
                        isOfflineMode = offlineMode,
                        notificationsEnabled = notifications,
                        autoPlayVideo = autoPlay,
                        selectedLanguage = language,
                        dailyGoal = goal,
                        isSignedIn = firebaseAuth.currentUser != null,
                        userEmail = firebaseAuth.currentUser?.email
                    )
                }
            }.collect()
        }
    }
    
    private fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.setDarkMode(enabled)
        }
    }
    
    private fun toggleOfflineMode(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.setOfflineMode(enabled)
        }
    }
    
    private fun toggleNotifications(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.setNotificationEnabled(enabled)
        }
    }
    
    private fun toggleAutoPlayVideo(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.setAutoPlayVideo(enabled)
        }
    }
    
    private fun selectLanguage(language: String) {
        viewModelScope.launch {
            preferencesManager.setLanguage(language)
        }
    }
    
    private fun setDailyGoal(goal: Int) {
        viewModelScope.launch {
            preferencesManager.setDailyGoal(goal)
        }
    }
    
    private fun syncData() {
        syncManager.syncNow()
        sendEffect(SettingsEffect.ShowMessage("Syncing data..."))
    }
    
    private fun signOut() {
        authRepository.signOut()
        setState { copy(isSignedIn = false, userEmail = null) }
        sendEffect(SettingsEffect.ShowMessage("Signed out successfully"))
    }
}