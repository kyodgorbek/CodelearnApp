package com.yodgorbek.codelearnapp.presentation.coursedetail

import com.yodgorbek.codelearnapp.presentation.mvi.UiIntent

sealed class CourseDetailIntent : UiIntent {
    data class LoadCourse(val courseId: String) : CourseDetailIntent()
    data class LessonClicked(val lessonId: String) : CourseDetailIntent()
    object BackPressed : CourseDetailIntent()
}
