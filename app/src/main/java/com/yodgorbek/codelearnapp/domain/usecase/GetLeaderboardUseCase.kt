package com.yodgorbek.codelearnapp.domain.usecase

import com.yodgorbek.codelearnapp.data.remote.FirestoreRepository
import com.yodgorbek.codelearnapp.domain.model.LeaderboardEntry
import kotlinx.coroutines.flow.Flow

class GetLeaderboardUseCase(
    private val firestoreRepository: FirestoreRepository
) {
    operator fun invoke(limit: Int = 50): Flow<List<LeaderboardEntry>> =
        firestoreRepository.getLeaderboard(limit)
}

