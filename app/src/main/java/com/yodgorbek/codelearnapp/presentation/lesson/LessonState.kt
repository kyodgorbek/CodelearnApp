package com.yodgorbek.codelearnapp.presentation.lesson

import com.yodgorbek.codelearnapp.domain.model.Lesson
import com.yodgorbek.codelearnapp.presentation.mvi.UiState

data class LessonState(
    val isLoading: Boolean = true,
    val lesson: Lesson? = null,
    val selectedAnswer: Int? = null,
    val showQuizResult: Boolean = false,
    val showCelebration: Boolean = false,
    val isMajorMilestone: Boolean = false,
    val milestoneReached: String? = null,
    val autoPlayVideo: Boolean = true,
    val currentCode: String = "",
    val executionOutput: String = "",
    val isExecuting: Boolean = false,
    val hasExecuted: Boolean = false,
    val error: String? = null,
    val codeError: com.yodgorbek.codelearnapp.presentation.tutor.analysis.CodeError? = null
) : UiState
