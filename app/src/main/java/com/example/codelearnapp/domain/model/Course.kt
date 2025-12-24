package com.example.codelearnapp.domain.model

data class Course(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val progress: Float,
    val totalLessons: Int,
    val completedLessons: Int,
    val category: CourseCategory
)

enum class CourseCategory {
    PYTHON, KOTLIN, JAVA, JAVASCRIPT, SQL
}