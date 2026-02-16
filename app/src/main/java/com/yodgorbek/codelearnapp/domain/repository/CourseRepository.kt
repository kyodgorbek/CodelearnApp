package com.yodgorbek.codelearnapp.domain.repository

import com.yodgorbek.codelearnapp.domain.model.Course
import com.yodgorbek.codelearnapp.domain.model.Lesson
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun getAllCourses(): Flow<List<Course>>
    fun getCourseById(id: String): Flow<Course?>
    fun getLessonsByCourse(courseId: String): Flow<List<Lesson>>
    fun getLessonById(lessonId: String): Flow<Lesson?>
    suspend fun updateLessonProgress(lessonId: String, isCompleted: Boolean)
    fun searchLessons(query: String): Flow<List<Lesson>>
    fun getBookmarkedLessons(): Flow<List<Lesson>>
    suspend fun toggleBookmark(lessonId: String, isBookmarked: Boolean)
    fun getUserProgress(): Flow<com.yodgorbek.codelearnapp.domain.model.UserProgress?>
}
