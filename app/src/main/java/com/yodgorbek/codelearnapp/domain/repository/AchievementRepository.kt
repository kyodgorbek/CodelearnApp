package com.yodgorbek.codelearnapp.domain.repository

import com.yodgorbek.codelearnapp.data.local.entity.AchievementEntity
import kotlinx.coroutines.flow.Flow

interface AchievementRepository {
    fun getAllAchievements(): Flow<List<AchievementEntity>>
    fun getUnlockedAchievements(): Flow<List<AchievementEntity>>
    suspend fun checkAndUnlockAchievements(lessonsCompleted: Int, totalXp: Int, currentStreak: Int)
}
