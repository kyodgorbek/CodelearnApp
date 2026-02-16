package com.yodgorbek.codelearnapp.domain.usecase

import com.yodgorbek.codelearnapp.domain.model.Lesson
import com.yodgorbek.codelearnapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class SearchLessonsUseCase(private val repository: CourseRepository) {
    operator fun invoke(query: String): Flow<List<Lesson>> =
        repository.searchLessons(query)
}
