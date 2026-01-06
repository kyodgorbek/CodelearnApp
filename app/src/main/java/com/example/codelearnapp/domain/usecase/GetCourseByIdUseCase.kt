package com.example.codelearnapp.domain.usecase

import com.example.codelearnapp.domain.model.Course
import com.example.codelearnapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetCourseByIdUseCase(private val repository: CourseRepository) {
    operator fun invoke(courseId: String): Flow<Course?> = 
        repository.getCourseById(courseId)
}