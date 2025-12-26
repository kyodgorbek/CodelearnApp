package com.example.codelearnapp.presentation.bookmarks

import com.example.codelearnapp.presentation.mvi.UiEffect

sealed class BookmarksEffect : UiEffect {
    data class NavigateToLesson(val lessonId: String) : BookmarksEffect()
    data class ShowMessage(val message: String) : BookmarksEffect()
}