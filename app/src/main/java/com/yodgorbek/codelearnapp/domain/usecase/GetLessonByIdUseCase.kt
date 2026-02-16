package com.yodgorbek.codelearnapp.domain.usecase

import com.yodgorbek.codelearnapp.domain.model.Lesson
import com.yodgorbek.codelearnapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetLessonByIdUseCase(private val repository: CourseRepository) {
    operator fun invoke(lessonId: String): Flow<Lesson?> =
        repository.getLessonById(lessonId)
}
