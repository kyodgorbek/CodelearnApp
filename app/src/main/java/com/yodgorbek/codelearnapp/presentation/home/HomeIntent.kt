package com.yodgorbek.codelearnapp.presentation.home

import com.yodgorbek.codelearnapp.presentation.mvi.UiIntent

sealed class HomeIntent : UiIntent {
    object LoadCourses : HomeIntent()
    data class CourseClicked(val courseId: String) : HomeIntent()
}
