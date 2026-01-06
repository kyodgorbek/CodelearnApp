package com.example.codelearnapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievements")
data class AchievementEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val isUnlocked: Boolean = false,
    val unlockedAt: Long? = null,
    val requiredProgress: Int,
    val currentProgress: Int = 0,
    val type: String // XP, LESSONS, STREAK, COURSES
)
