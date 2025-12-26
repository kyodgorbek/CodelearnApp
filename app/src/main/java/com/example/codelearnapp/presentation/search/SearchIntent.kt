package com.example.codelearnapp.presentation.search

import com.example.codelearnapp.domain.model.Lesson
import com.example.codelearnapp.presentation.mvi.*

sealed class SearchIntent : UiIntent {
    data class Search(val query: String) : SearchIntent()
    object ClearSearch : SearchIntent()
    data class LessonClicked(val lessonId: String) : SearchIntent()
}