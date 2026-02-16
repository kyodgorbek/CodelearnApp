package com.yodgorbek.codelearnapp.presentation.achievements

import com.yodgorbek.codelearnapp.data.local.entity.AchievementEntity
import com.yodgorbek.codelearnapp.presentation.mvi.UiState

data class AchievementsState(
    val isLoading: Boolean = true,
    val achievements: List<AchievementEntity> = emptyList(),
    val error: String? = null
) : UiState
