package com.yodgorbek.codelearnapp.presentation.coursedetail

import com.yodgorbek.codelearnapp.presentation.mvi.UiEffect

sealed class CourseDetailEffect : UiEffect {
    data class NavigateToLesson(val lessonId: String) : CourseDetailEffect()
    object NavigateBack : CourseDetailEffect()
}
