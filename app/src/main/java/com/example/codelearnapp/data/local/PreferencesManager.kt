package com.example.codelearnapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class PreferencesManager(private val context: Context) {
    
    companion object {
        private val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
        private val IS_OFFLINE_MODE = booleanPreferencesKey("is_offline_mode")
        private val SELECTED_LANGUAGE = stringPreferencesKey("selected_language")
        private val NOTIFICATION_ENABLED = booleanPreferencesKey("notification_enabled")
        private val DAILY_GOAL = intPreferencesKey("daily_goal")
        private val AUTO_PLAY_VIDEO = booleanPreferencesKey("auto_play_video")
        private val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
    private val USER_MOTIVATION = stringPreferencesKey("user_motivation")
    private val USER_EXPERIENCE = stringPreferencesKey("user_experience")
    private val USER_CAREER_PATH = stringPreferencesKey("user_career_path")
    private val USER_ROLE = stringPreferencesKey("user_role")
    private val USER_INTEREST_TYPE = stringPreferencesKey("user_interest_type")
    private val USER_INTEREST_TOPIC = stringPreferencesKey("user_interest_topic")
    }
    
    val isDarkMode: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[IS_DARK_MODE] ?: false }
    
    val isOfflineMode: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[IS_OFFLINE_MODE] ?: false }
    
    val selectedLanguage: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[SELECTED_LANGUAGE] ?: "en" }
    
    val notificationEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[NOTIFICATION_ENABLED] ?: true }
    
    val dailyGoal: Flow<Int> = context.dataStore.data
        .map { preferences -> preferences[DAILY_GOAL] ?: 3 }
    
    val autoPlayVideo: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[AUTO_PLAY_VIDEO] ?: true }
    
    val isOnboardingCompleted: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[ONBOARDING_COMPLETED] ?: false }
    
    suspend fun setDarkMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_DARK_MODE] = enabled
        }
    }
    
    suspend fun setOfflineMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_OFFLINE_MODE] = enabled
        }
    }
    
    suspend fun setLanguage(language: String) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_LANGUAGE] = language
        }
    }
    
    suspend fun setNotificationEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[NOTIFICATION_ENABLED] = enabled
        }
    }
    
    suspend fun setDailyGoal(goal: Int) {
        context.dataStore.edit { preferences ->
            preferences[DAILY_GOAL] = goal
        }
    }
    
    suspend fun setAutoPlayVideo(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[AUTO_PLAY_VIDEO] = enabled
        }
    }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_COMPLETED] = completed
        }
    }

    suspend fun setUserMotivation(motivation: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_MOTIVATION] = motivation
        }
    }

    suspend fun setUserExperience(experience: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EXPERIENCE] = experience
        }
    }

    suspend fun setUserCareerPath(path: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_CAREER_PATH] = path
        }
    }

    suspend fun setUserRole(role: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ROLE] = role
        }
    }

    suspend fun setUserInterestType(type: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_INTEREST_TYPE] = type
        }
    }

    suspend fun setUserInterestTopic(topic: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_INTEREST_TOPIC] = topic
        }
    }

    suspend fun setDailyGoal(minutes: Int) {
        context.dataStore.edit { preferences ->
            preferences[DAILY_GOAL] = minutes
        }
    }
}
