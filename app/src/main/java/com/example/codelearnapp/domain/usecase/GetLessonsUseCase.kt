package com.example.codelearnapp.domain.usecase

import com.example.codelearnapp.domain.model.Lesson
import com.example.codelearnapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetLessonsUseCase(private val repository: CourseRepository) {
    operator fun invoke(courseId: String): Flow<List<Lesson>> =
        repository.getLessonsByCourse(courseId)
}