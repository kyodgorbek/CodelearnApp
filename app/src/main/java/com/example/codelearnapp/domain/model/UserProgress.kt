package com.example.codelearnapp.domain.model

data class UserProgress(
    val totalXp: Int = 0,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val coursesCompleted: Int = 0,
    val lessonsCompleted: Int = 0
)