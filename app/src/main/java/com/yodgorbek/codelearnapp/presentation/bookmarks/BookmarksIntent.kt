package com.yodgorbek.codelearnapp.presentation.bookmarks

import com.yodgorbek.codelearnapp.presentation.mvi.UiIntent

sealed class BookmarksIntent : UiIntent {
    object LoadBookmarks : BookmarksIntent()
    data class RemoveBookmark(val lessonId: String) : BookmarksIntent()
    data class LessonClicked(val lessonId: String) : BookmarksIntent()
}
