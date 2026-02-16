package com.yodgorbek.codelearnapp.presentation.achievements

import androidx.lifecycle.viewModelScope
import com.yodgorbek.codelearnapp.data.local.entity.AchievementEntity
import com.yodgorbek.codelearnapp.domain.usecase.GetAchievementsUseCase
import com.yodgorbek.codelearnapp.presentation.mvi.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

sealed class AchievementsIntent : UiIntent {
    object LoadAchievements : AchievementsIntent()
}
