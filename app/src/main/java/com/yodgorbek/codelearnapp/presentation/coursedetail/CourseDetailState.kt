package com.yodgorbek.codelearnapp.presentation.coursedetail

import com.yodgorbek.codelearnapp.domain.model.Course
import com.yodgorbek.codelearnapp.domain.model.Lesson
import com.yodgorbek.codelearnapp.presentation.mvi.UiState

data class CourseDetailState(
    val isLoading: Boolean = true,
    val course: Course? = null,
    val lessons: List<Lesson> = emptyList(),
    val error: String? = null
) : UiState
