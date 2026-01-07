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

enum class CourseCategory(val displayName: String) {
    PYTHON("Python"),
    KOTLIN("Kotlin"),
    JAVA("Java"),
    JAVASCRIPT("JavaScript"),
    SQL("SQL"),
    DATA_SCIENCE("Data Science"),
    FOR_KIDS("For Kids"),
    JAVA_DSA("Java DSA"),
    KOTLIN_DSA("Kotlin DSA"),
    JETPACK_COMPOSE("Jetpack Compose")
}