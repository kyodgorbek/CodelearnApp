package com.example.codelearnapp.domain.repository

import com.example.codelearnapp.domain.model.Course
import com.example.codelearnapp.domain.model.Lesson
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun getAllCourses(): Flow<List<Course>>
    fun getCourseById(id: String): Flow<Course?>
    fun getLessonsByCourse(courseId: String): Flow<List<Lesson>>
    fun getLessonById(lessonId: String): Flow<Lesson?>
    suspend fun updateLessonProgress(lessonId: String, isCompleted: Boolean)
}