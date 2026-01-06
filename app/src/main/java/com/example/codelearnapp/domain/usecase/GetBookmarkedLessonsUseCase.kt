package com.example.codelearnapp.domain.usecase

import com.example.codelearnapp.domain.model.Lesson
import com.example.codelearnapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetBookmarkedLessonsUseCase(private val repository: CourseRepository) {
    operator fun invoke(): Flow<List<Lesson>> = repository.getBookmarkedLessons()
}

