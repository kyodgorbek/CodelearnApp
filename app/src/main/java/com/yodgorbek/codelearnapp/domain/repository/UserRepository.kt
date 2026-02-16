// domain/repository/UserRepository.kt
package com.yodgorbek.codelearnapp.domain.repository

import com.yodgorbek.codelearnapp.domain.model.UserProgress
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserProgress(): Flow<UserProgress>
    suspend fun updateXP(points: Int)
    suspend fun updateStreak()
    suspend fun completeLesson(lessonId: String)
}
