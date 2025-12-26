package com.example.codelearnapp.presentation.leaderboard

import com.example.codelearnapp.presentation.mvi.UiEffect

sealed class LeaderboardEffect : UiEffect {
    data class ShowError(val message: String) : LeaderboardEffect()
}