package com.example.codelearnapp.domain.usecase

import com.example.codelearnapp.domain.repository.CourseRepository

class CompleteLessonUseCase(private val repository: CourseRepository) {
    suspend operator fun invoke(lessonId: String) {
        repository.updateLessonProgress(lessonId, true)
    }
}