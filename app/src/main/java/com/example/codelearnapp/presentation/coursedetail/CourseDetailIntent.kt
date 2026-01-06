package com.example.codelearnapp.presentation.coursedetail

import com.example.codelearnapp.presentation.mvi.UiIntent

sealed class CourseDetailIntent : UiIntent {
    data class LoadCourse(val courseId: String) : CourseDetailIntent()
    data class LessonClicked(val lessonId: String) : CourseDetailIntent()
    object BackPressed : CourseDetailIntent()
}