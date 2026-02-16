package com.yodgorbek.codelearnapp.domain.usecase

import com.yodgorbek.codelearnapp.domain.model.Course
import com.yodgorbek.codelearnapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetCoursesUseCase(private val repository: CourseRepository) {
    operator fun invoke(): Flow<List<Course>> = repository.getAllCourses()
}
