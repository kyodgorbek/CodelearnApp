package com.example.codelearnapp.domain.usecase

import com.example.codelearnapp.domain.model.UserProgress
import com.example.codelearnapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetUserProgressUseCase(private val repository: CourseRepository) {
    operator fun invoke(): Flow<UserProgress?> {
        return repository.getUserProgress()
    }
}
