package com.example.codelearnapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_progress")
data class UserProgressEntity(
    @PrimaryKey
    val userId: String = "local_user",
    val totalXp: Int = 0,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val coursesCompleted: Int = 0,
    val lessonsCompleted: Int = 0,
    val lastActivityDate: Long = System.currentTimeMillis()
)