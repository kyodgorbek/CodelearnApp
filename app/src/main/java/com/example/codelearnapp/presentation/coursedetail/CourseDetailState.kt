package com.example.codelearnapp.presentation.coursedetail

import com.example.codelearnapp.domain.model.Course
import com.example.codelearnapp.domain.model.Lesson
import com.example.codelearnapp.presentation.mvi.UiState

data class CourseDetailState(
    val isLoading: Boolean = true,
    val course: Course? = null,
    val lessons: List<Lesson> = emptyList(),
    val error: String? = null
) : UiState