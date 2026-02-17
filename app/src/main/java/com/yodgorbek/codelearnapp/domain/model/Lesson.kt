package com.yodgorbek.codelearnapp.domain.model

data class Lesson(
    val id: String,
    val courseId: String,
    val title: String,
    val content: String,
    val type: LessonType,
    val order: Int,
    val isCompleted: Boolean,
    val codeExample: String? = null,
    val quiz: Quiz? = null,
    val videoUrl: String? = null,
    val language: String = "kotlin",
    val defaultInput: String = ""
)
enum class LessonType {
    THEORY, CODE_PRACTICE, QUIZ, CHALLENGE
}
