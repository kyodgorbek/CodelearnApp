package com.yodgorbek.codelearnapp.presentation.search

import com.yodgorbek.codelearnapp.domain.model.Lesson
import com.yodgorbek.codelearnapp.presentation.mvi.UiState

data class SearchState(
    val isLoading: Boolean = false,
    val query: String = "",
    val results: List<Lesson> = emptyList(),
    val recentSearches: List<String> = emptyList()
) : UiState
