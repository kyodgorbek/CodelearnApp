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
}
