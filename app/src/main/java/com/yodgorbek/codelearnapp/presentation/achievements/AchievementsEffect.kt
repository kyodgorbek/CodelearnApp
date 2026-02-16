package com.yodgorbek.codelearnapp.presentation.achievements

import com.yodgorbek.codelearnapp.data.local.entity.AchievementEntity
import com.yodgorbek.codelearnapp.presentation.mvi.UiEffect

sealed class AchievementsEffect : UiEffect {
    data class ShowUnlockAnimation(val achievement: AchievementEntity) : AchievementsEffect()
}
