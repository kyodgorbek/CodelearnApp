package com.example.codelearnapp.presentation.achievements

import androidx.lifecycle.viewModelScope
import com.example.codelearnapp.data.local.entity.AchievementEntity
import com.example.codelearnapp.domain.usecase.GetAchievementsUseCase
import com.example.codelearnapp.presentation.mvi.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

sealed class AchievementsIntent : UiIntent {
    object LoadAchievements : AchievementsIntent()
}