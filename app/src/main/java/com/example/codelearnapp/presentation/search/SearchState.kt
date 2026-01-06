package com.example.codelearnapp.presentation.search

import com.example.codelearnapp.domain.model.Lesson
import com.example.codelearnapp.presentation.mvi.UiState

data class SearchState(
    val isLoading: Boolean = false,
    val query: String = "",
    val results: List<Lesson> = emptyList(),
    val recentSearches: List<String> = emptyList()
) : UiState