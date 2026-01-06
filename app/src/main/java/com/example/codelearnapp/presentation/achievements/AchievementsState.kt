package com.example.codelearnapp.presentation.achievements

import com.example.codelearnapp.data.local.entity.AchievementEntity
import com.example.codelearnapp.presentation.mvi.UiState

data class AchievementsState(
    val isLoading: Boolean = true,
    val achievements: List<AchievementEntity> = emptyList(),
    val error: String? = null
) : UiState
