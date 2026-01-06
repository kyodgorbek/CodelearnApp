package com.example.codelearnapp.domain.usecase

import com.example.codelearnapp.data.remote.FirestoreRepository
import com.example.codelearnapp.domain.model.LeaderboardEntry
import kotlinx.coroutines.flow.Flow

class GetLeaderboardUseCase(
    private val firestoreRepository: FirestoreRepository
) {
    operator fun invoke(limit: Int = 50): Flow<List<LeaderboardEntry>> = 
        firestoreRepository.getLeaderboard(limit)
}

