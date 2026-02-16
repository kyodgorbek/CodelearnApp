package com.yodgorbek.codelearnapp.domain.usecase

import com.yodgorbek.codelearnapp.data.local.entity.AchievementEntity
import com.yodgorbek.codelearnapp.domain.repository.AchievementRepository
import kotlinx.coroutines.flow.Flow

class GetAchievementsUseCase(private val repository: AchievementRepository) {
    operator fun invoke(): Flow<List<AchievementEntity>> = repository.getAllAchievements()
}
