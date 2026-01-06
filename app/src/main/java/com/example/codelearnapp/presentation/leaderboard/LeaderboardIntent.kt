package com.example.codelearnapp.presentation.leaderboard

import androidx.lifecycle.viewModelScope
import com.example.codelearnapp.domain.model.LeaderboardEntry
import com.example.codelearnapp.domain.usecase.GetLeaderboardUseCase
import com.example.codelearnapp.presentation.mvi.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

sealed class LeaderboardIntent : UiIntent {
    object LoadLeaderboard : LeaderboardIntent()
    object Refresh : LeaderboardIntent()
}