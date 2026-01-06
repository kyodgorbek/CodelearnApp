package com.example.codelearnapp.domain.usecase

import com.example.codelearnapp.domain.repository.CourseRepository

class ToggleBookmarkUseCase(private val repository: CourseRepository) {
    suspend operator fun invoke(lessonId: String, isBookmarked: Boolean) {
        repository.toggleBookmark(lessonId, isBookmarked)
    }
}