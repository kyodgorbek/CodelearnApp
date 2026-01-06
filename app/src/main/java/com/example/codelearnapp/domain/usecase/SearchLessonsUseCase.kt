package com.example.codelearnapp.domain.usecase

import com.example.codelearnapp.domain.model.Lesson
import com.example.codelearnapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class SearchLessonsUseCase(private val repository: CourseRepository) {
    operator fun invoke(query: String): Flow<List<Lesson>> = 
        repository.searchLessons(query)
}