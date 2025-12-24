// domain/repository/UserRepository.kt
package com.example.codelearnapp.domain.repository

import com.example.codelearnapp.domain.model.UserProgress
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserProgress(): Flow<UserProgress>
    suspend fun updateXP(points: Int)
    suspend fun updateStreak()
    suspend fun completeLesson(lessonId: String)
}