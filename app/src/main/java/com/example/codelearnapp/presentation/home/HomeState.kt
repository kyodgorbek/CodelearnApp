package com.example.codelearnapp.presentation.home

import com.example.codelearnapp.domain.model.Course
import com.example.codelearnapp.presentation.mvi.UiState

data class HomeState(
    val isLoading: Boolean = true,
    val courses: List<Course> = emptyList(),
    val error: String? = null
) : UiState