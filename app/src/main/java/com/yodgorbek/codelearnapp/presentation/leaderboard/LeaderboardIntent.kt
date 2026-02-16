package com.yodgorbek.codelearnapp.presentation.leaderboard

import com.yodgorbek.codelearnapp.domain.model.LeaderboardEntry
import com.yodgorbek.codelearnapp.domain.usecase.GetLeaderboardUseCase
import com.yodgorbek.codelearnapp.presentation.mvi.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

sealed class LeaderboardIntent : UiIntent {
    object LoadLeaderboard : LeaderboardIntent()
    object Refresh : LeaderboardIntent()
}
