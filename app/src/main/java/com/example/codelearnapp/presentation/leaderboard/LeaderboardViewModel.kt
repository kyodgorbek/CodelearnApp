package com.example.codelearnapp.presentation.leaderboard

import androidx.lifecycle.viewModelScope
import com.example.codelearnapp.domain.usecase.GetLeaderboardUseCase
import com.example.codelearnapp.presentation.mvi.MviViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LeaderboardViewModel(
    private val getLeaderboardUseCase: GetLeaderboardUseCase,
    private val firebaseAuth: FirebaseAuth
) : MviViewModel<LeaderboardIntent, LeaderboardState, LeaderboardEffect>() {
    
    override fun createInitialState(): LeaderboardState = LeaderboardState(
        currentUserId = firebaseAuth.currentUser?.uid
    )
    
    init {
        sendIntent(LeaderboardIntent.LoadLeaderboard)
    }
    
    override fun handleIntent(intent: LeaderboardIntent) {
        when (intent) {
            is LeaderboardIntent.LoadLeaderboard -> loadLeaderboard()
            is LeaderboardIntent.Refresh -> loadLeaderboard()
        }
    }
    
    private fun loadLeaderboard() {
        setState { copy(isLoading = true) }
        
        viewModelScope.launch {
            getLeaderboardUseCase(50)
                .catch { e ->
                    setState { copy(isLoading = false, error = e.message) }
                    sendEffect(LeaderboardEffect.ShowError(e.message ?: "Failed to load leaderboard"))
                }
                .collect { entries ->
                    setState {
                        copy(
                            isLoading = false,
                            entries = entries,
                            error = null
                        )
                    }
                }
        }
    }
}