package com.example.codelearnapp.domain.usecase

import com.example.codelearnapp.domain.model.Course
import com.example.codelearnapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetCoursesUseCase(private val repository: CourseRepository) {
    operator fun invoke(): Flow<List<Course>> = repository.getAllCourses()
}