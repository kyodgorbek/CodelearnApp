package com.example.codelearnapp.data.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.codelearnapp.data.local.AppDatabase
import com.example.codelearnapp.data.remote.FirebaseAuthRepository
import com.example.codelearnapp.data.remote.FirestoreRepository
import kotlinx.coroutines.flow.first
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params), KoinComponent {
    
    private val database: AppDatabase by inject()
    private val authRepository: FirebaseAuthRepository by inject()
    private val firestoreRepository: FirestoreRepository by inject()
    
    override suspend fun doWork(): Result {
        return try {
            val user = authRepository.currentUser ?: return Result.success()
            
            // Sync user progress
            val progress = database.userProgressDao().getUserProgress().first()
            progress?.let {
                firestoreRepository.updateUserXp(user.uid, it.totalXp)
                firestoreRepository.updateLeaderboard(
                    user.uid,
                    user.displayName ?: user.email ?: "Anonymous",
                    it.totalXp
                )
            }
            
            // Sync completed lessons
            val completedLessons = database.lessonDao().getCompletedLessons().first()
            completedLessons.forEach { lesson ->
                firestoreRepository.syncLessonProgress(user.uid, lesson.id, lesson.isCompleted)
            }
            
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}