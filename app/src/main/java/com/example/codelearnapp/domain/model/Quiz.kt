// domain/model/Quiz.kt
package com.example.codelearnapp.domain.model

data class Quiz(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String
)