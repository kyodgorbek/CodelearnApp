package com.example.codelearnapp.data.local.dao

import androidx.room.*
import com.example.codelearnapp.data.local.entity.UserProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProgressDao {
    @Query("SELECT * FROM user_progress WHERE userId = :userId")
    fun getUserProgress(userId: String = "local_user"): Flow<UserProgressEntity?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: UserProgressEntity)
    
    @Update
    suspend fun updateProgress(progress: UserProgressEntity)
    
    @Query("UPDATE user_progress SET totalXp = totalXp + :xp WHERE userId = :userId")
    suspend fun addXp(xp: Int, userId: String = "local_user")
    
    @Query("UPDATE user_progress SET currentStreak = :streak, lastActivityDate = :date WHERE userId = :userId")
    suspend fun updateStreak(streak: Int, date: Long, userId: String = "local_user")
    
    @Query("UPDATE user_progress SET lessonsCompleted = lessonsCompleted + 1 WHERE userId = :userId")
    suspend fun incrementLessonsCompleted(userId: String = "local_user")
}
