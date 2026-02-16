package com.yodgorbek.codelearnapp.domain.usecase

import com.yodgorbek.codelearnapp.domain.model.UserProgress
import com.yodgorbek.codelearnapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetUserProgressUseCase(private val repository: CourseRepository) {
    operator fun invoke(): Flow<UserProgress?> {
        return repository.getUserProgress()
    }
}
