package com.example.codelearnapp.domain.usecase

import com.example.codelearnapp.domain.repository.AchievementRepository

class CheckAchievementsUseCase(private val repository: AchievementRepository) {
    suspend operator fun invoke(lessonsCompleted: Int, totalXp: Int, currentStreak: Int) {
        repository.checkAndUnlockAchievements(lessonsCompleted, totalXp, currentStreak)
    }
}