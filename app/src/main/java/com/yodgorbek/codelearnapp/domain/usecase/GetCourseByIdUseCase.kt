package com.yodgorbek.codelearnapp.domain.usecase

import com.yodgorbek.codelearnapp.domain.model.Course
import com.yodgorbek.codelearnapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetCourseByIdUseCase(private val repository: CourseRepository) {
    operator fun invoke(courseId: String): Flow<Course?> =
        repository.getCourseById(courseId)
}
