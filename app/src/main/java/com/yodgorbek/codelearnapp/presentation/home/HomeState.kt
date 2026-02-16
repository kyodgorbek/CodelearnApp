package com.yodgorbek.codelearnapp.presentation.home

import com.yodgorbek.codelearnapp.domain.model.Course
import com.yodgorbek.codelearnapp.presentation.mvi.UiState

data class HomeState(
    val isLoading: Boolean = true,
    val courses: List<Course> = emptyList(),
    val error: String? = null
) : UiState
