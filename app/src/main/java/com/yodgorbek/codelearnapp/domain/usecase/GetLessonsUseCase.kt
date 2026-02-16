package com.yodgorbek.codelearnapp.domain.usecase

import com.yodgorbek.codelearnapp.domain.model.Lesson
import com.yodgorbek.codelearnapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetLessonsUseCase(private val repository: CourseRepository) {
    operator fun invoke(courseId: String): Flow<List<Lesson>> =
        repository.getLessonsByCourse(courseId)
}
