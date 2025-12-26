package com.example.codelearnapp.presentation.achievements

import androidx.lifecycle.viewModelScope
import com.example.codelearnapp.domain.usecase.GetAchievementsUseCase
import com.example.codelearnapp.presentation.mvi.MviViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AchievementsViewModel(
    private val getAchievementsUseCase: GetAchievementsUseCase
) : MviViewModel<AchievementsIntent, AchievementsState, AchievementsEffect>() {
    
    override fun createInitialState(): AchievementsState = AchievementsState()
    
    init {
        sendIntent(AchievementsIntent.LoadAchievements)
    }
    
    override fun handleIntent(intent: AchievementsIntent) {
        when (intent) {
            is AchievementsIntent.LoadAchievements -> loadAchievements()
        }
    }
    
    private fun loadAchievements() {
        viewModelScope.launch {
            getAchievementsUseCase()
                .catch { e ->
                    setState { copy(isLoading = false, error = e.message) }
                }
                .collect { achievements ->
                    setState {
                        copy(
                            isLoading = false,
                            achievements = achievements,
                            error = null
                        )
                    }
                }
        }
    }
}