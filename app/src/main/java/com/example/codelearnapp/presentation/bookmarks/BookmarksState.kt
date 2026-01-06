package com.example.codelearnapp.presentation.bookmarks

import com.example.codelearnapp.domain.model.Lesson
import com.example.codelearnapp.presentation.mvi.UiState

data class BookmarksState(
    val isLoading: Boolean = true,
    val bookmarkedLessons: List<Lesson> = emptyList(),
    val error: String? = null
) : UiState