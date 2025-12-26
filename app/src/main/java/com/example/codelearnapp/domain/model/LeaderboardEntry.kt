package com.example.codelearnapp.domain.model

data class LeaderboardEntry(
    val id: String = "",
    val userName: String = "",
    val totalXp: Int = 0,
    val lastUpdated: Long = 0
)