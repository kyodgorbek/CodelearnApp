package com.example.codelearnapp.domain.usecase

import com.example.codelearnapp.data.local.entity.AchievementEntity
import com.example.codelearnapp.domain.repository.AchievementRepository
import kotlinx.coroutines.flow.Flow

class GetAchievementsUseCase(private val repository: AchievementRepository) {
    operator fun invoke(): Flow<List<AchievementEntity>> = repository.getAllAchievements()
}
