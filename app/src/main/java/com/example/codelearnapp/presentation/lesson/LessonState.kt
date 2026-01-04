package com.example.codelearnapp.presentation.lesson

import com.example.codelearnapp.domain.model.Lesson
import com.example.codelearnapp.presentation.mvi.UiState

data class LessonState(
    val isLoading: Boolean = true,
    val lesson: Lesson? = null,
    val selectedAnswer: Int? = null,
    val showQuizResult: Boolean = false,
    val showCelebration: Boolean = false,
    val isMajorMilestone: Boolean = false,
    val milestoneReached: String? = null,
    val autoPlayVideo: Boolean = true,
    val error: String? = null
) : UiState