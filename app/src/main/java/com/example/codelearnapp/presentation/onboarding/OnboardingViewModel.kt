package com.example.codelearnapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codelearnapp.data.local.PreferencesManager
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class OnboardingState(
    val motivation: String = "",
    val role: String = "",
    val interestType: String = "",
    val interestTopic: String = "",
    val experience: String = "",
    val careerPath: String = "",
    val recommendedPath: String = "", // Calculated based on answers
    val dailyGoal: Int = 10,
    val reminderTime: String = "18:00"
)

class OnboardingViewModel(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _state = MutableStateFlow(OnboardingState())
    val state = _state.asStateFlow()

    fun updateMotivation(motivation: String) {
        _state.update { it.copy(motivation = motivation) }
    }

    fun updateRole(role: String) {
        _state.update { it.copy(role = role) }
    }

    fun updateInterestType(type: String) {
        _state.update { it.copy(interestType = type) }
    }

    fun updateInterestTopic(topic: String) {
        _state.update { 
            it.copy(
                interestTopic = topic,
                recommendedPath = calculateRecommendedPath(topic)
            ) 
        }
    }

    fun updateExperience(experience: String) {
        _state.update { it.copy(experience = experience) }
    }

    fun updateCareerPath(path: String) {
        _state.update { it.copy(careerPath = path) }
    }

    fun updateDailyGoal(minutes: Int) {
        _state.update { it.copy(dailyGoal = minutes) }
    }

    fun updateReminderTime(time: String) {
        _state.update { it.copy(reminderTime = time) }
    }

    private fun calculateRecommendedPath(topic: String): String {
        return when (topic) {
            "web", "apps", "design" -> "web"
            "data", "ai", "auto" -> "python"
            else -> "web" // Default
        }
    }

    fun completeOnboarding() {
        viewModelScope.launch {
            val currentState = state.value
            preferencesManager.setUserMotivation(currentState.motivation)
            preferencesManager.setUserRole(currentState.role)
            preferencesManager.setUserInterestType(currentState.interestType)
            preferencesManager.setUserInterestTopic(currentState.interestTopic)
            preferencesManager.setUserExperience(currentState.experience)
            preferencesManager.setUserCareerPath(currentState.careerPath)
            preferencesManager.setDailyGoal(currentState.dailyGoal)
            
            // Note: Reminder scheduling would happen here using ReminderManager
            preferencesManager.setOnboardingCompleted(true)
        }
    }
}
