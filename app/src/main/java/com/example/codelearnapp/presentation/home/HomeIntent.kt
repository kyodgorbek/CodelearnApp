package com.example.codelearnapp.presentation.home

import com.example.codelearnapp.presentation.mvi.UiIntent

sealed class HomeIntent : UiIntent {
    object LoadCourses : HomeIntent()
    data class CourseClicked(val courseId: String) : HomeIntent()
}