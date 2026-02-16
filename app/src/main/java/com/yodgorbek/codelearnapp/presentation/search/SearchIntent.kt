package com.yodgorbek.codelearnapp.presentation.search

import com.yodgorbek.codelearnapp.domain.model.Lesson
import com.yodgorbek.codelearnapp.presentation.mvi.*

sealed class SearchIntent : UiIntent {
    data class Search(val query: String) : SearchIntent()
    object ClearSearch : SearchIntent()
    data class LessonClicked(val lessonId: String) : SearchIntent()
}
