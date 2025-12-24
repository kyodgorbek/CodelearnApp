package com.example.codelearnapp.domain.usecase

import com.example.codelearnapp.domain.model.Lesson
import com.example.codelearnapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetLessonByIdUseCase(private val repository: CourseRepository) {
    operator fun invoke(lessonId: String): Flow<Lesson?> = 
        repository.getLessonById(lessonId)
}