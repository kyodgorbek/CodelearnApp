package com.yodgorbek.codelearnapp.presentation.leaderboard

import com.yodgorbek.codelearnapp.presentation.mvi.UiEffect

sealed class LeaderboardEffect : UiEffect {
    data class ShowError(val message: String) : LeaderboardEffect()
}
