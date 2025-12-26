package com.example.codelearnapp.data.repository

import com.example.codelearnapp.data.local.AppDatabase
import com.example.codelearnapp.data.local.entity.AchievementEntity
import com.example.codelearnapp.domain.repository.AchievementRepository
import kotlinx.coroutines.flow.Flow

class AchievementRepositoryImpl(
    private val database: AppDatabase
) : AchievementRepository {
    
    private val achievementDao = database.achievementDao()
    
    override fun getAllAchievements(): Flow<List<AchievementEntity>> {
        return achievementDao.getAllAchievements()
    }
    
    override fun getUnlockedAchievements(): Flow<List<AchievementEntity>> {
        return achievementDao.getUnlockedAchievements()
    }
    
    override suspend fun checkAndUnlockAchievements(
        lessonsCompleted: Int,
        totalXp: Int,
        currentStreak: Int
    ) {
        // Implementation moved to EnhancedCourseRepositoryImpl
    }
}