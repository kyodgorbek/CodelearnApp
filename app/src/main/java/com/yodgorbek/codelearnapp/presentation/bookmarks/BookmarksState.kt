package com.yodgorbek.codelearnapp.presentation.bookmarks

import com.yodgorbek.codelearnapp.domain.model.Lesson
import com.yodgorbek.codelearnapp.presentation.mvi.UiState

data class BookmarksState(
    val isLoading: Boolean = true,
    val bookmarkedLessons: List<Lesson> = emptyList(),
    val error: String? = null
) : UiState
