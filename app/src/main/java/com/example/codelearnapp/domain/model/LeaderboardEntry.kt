package com.example.codelearnapp.domain.model

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class LeaderboardEntry(
    val id: String = "",
    val userName: String = "",
    val totalXp: Int = 0,
    val lastUpdated: Long = 0
)