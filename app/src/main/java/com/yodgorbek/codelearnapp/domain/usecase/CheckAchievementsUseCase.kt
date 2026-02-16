package com.yodgorbek.codelearnapp.domain.usecase

import com.yodgorbek.codelearnapp.domain.repository.AchievementRepository

class CheckAchievementsUseCase(private val repository: AchievementRepository) {
    suspend operator fun invoke(lessonsCompleted: Int, totalXp: Int, currentStreak: Int) {
        repository.checkAndUnlockAchievements(lessonsCompleted, totalXp, currentStreak)
    }
}
