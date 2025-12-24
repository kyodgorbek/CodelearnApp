package com.example.codelearnapp.domain.model

data class Lesson(
    val id: String,
    val courseId: String,
    val title: String,
    val content: String,
    val type: LessonType,
    val order: Int,
    val isCompleted: Boolean,
    val codeExample: String? = null,
    val quiz: Quiz? = null
)
enum class LessonType {
    THEORY, CODE_PRACTICE, QUIZ, CHALLENGE
}