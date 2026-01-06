package com.example.codelearnapp.presentation.coursedetail

import com.example.codelearnapp.presentation.mvi.UiEffect

sealed class CourseDetailEffect : UiEffect {
    data class NavigateToLesson(val lessonId: String) : CourseDetailEffect()
    object NavigateBack : CourseDetailEffect()
}