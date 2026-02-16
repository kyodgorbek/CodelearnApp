package com.yodgorbek.codelearnapp.presentation.bookmarks

import androidx.lifecycle.viewModelScope
import com.yodgorbek.codelearnapp.domain.usecase.GetBookmarkedLessonsUseCase
import com.yodgorbek.codelearnapp.domain.usecase.ToggleBookmarkUseCase
import com.yodgorbek.codelearnapp.presentation.mvi.MviViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BookmarksViewModel(
    private val getBookmarkedLessonsUseCase: GetBookmarkedLessonsUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase
) : MviViewModel<BookmarksIntent, BookmarksState, BookmarksEffect>(BookmarksState()) {

    init {
        sendIntent(BookmarksIntent.LoadBookmarks)
    }

    override fun handleIntent(intent: BookmarksIntent) {
        when (intent) {
            is BookmarksIntent.LoadBookmarks -> loadBookmarks()
            is BookmarksIntent.RemoveBookmark -> removeBookmark(intent.lessonId)
            is BookmarksIntent.LessonClicked -> navigateToLesson(intent.lessonId)
        }
    }

    private fun loadBookmarks() {
        viewModelScope.launch {
            getBookmarkedLessonsUseCase()
                .catch { e ->
                    setState { copy(isLoading = false, error = e.message) }
                }
                .collect { lessons ->
                    setState {
                        copy(
                            isLoading = false,
                            bookmarkedLessons = lessons,
                            error = null
                        )
                    }
                }
        }
    }

    private fun removeBookmark(lessonId: String) {
        viewModelScope.launch {
            toggleBookmarkUseCase(lessonId, false)
            sendEffect(BookmarksEffect.ShowMessage("Bookmark removed"))
        }
    }

    private fun navigateToLesson(lessonId: String) {
        sendEffect(BookmarksEffect.NavigateToLesson(lessonId))
    }
}
