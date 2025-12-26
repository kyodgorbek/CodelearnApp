package com.example.codelearnapp.presentation.achievements

import com.example.codelearnapp.data.local.entity.AchievementEntity
import com.example.codelearnapp.presentation.mvi.UiEffect

sealed class AchievementsEffect : UiEffect {
    data class ShowUnlockAnimation(val achievement: AchievementEntity) : AchievementsEffect()
}