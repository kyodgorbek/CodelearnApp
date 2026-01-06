package com.example.codelearnapp.presentation.leaderboard

import com.example.codelearnapp.domain.model.LeaderboardEntry
import com.example.codelearnapp.presentation.mvi.UiState

data class LeaderboardState(
    val isLoading: Boolean = true,
    val entries: List<LeaderboardEntry> = emptyList(),
    val currentUserId: String? = null,
    val error: String? = null
) : UiState
