package com.example.codelearnapp.presentation.coursedetail

import androidx.lifecycle.viewModelScope
import com.example.codelearnapp.domain.usecase.GetCourseByIdUseCase
import com.example.codelearnapp.domain.usecase.GetLessonsUseCase
import com.example.codelearnapp.presentation.mvi.MviViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class CourseDetailViewModel(
    private val getCourseByIdUseCase: GetCourseByIdUseCase,
    private val getLessonsUseCase: GetLessonsUseCase
) : MviViewModel<CourseDetailIntent, CourseDetailState, CourseDetailEffect>() {
    
    override fun createInitialState(): CourseDetailState = CourseDetailState()
    
    override fun handleIntent(intent: CourseDetailIntent) {
        when (intent) {
            is CourseDetailIntent.LoadCourse -> loadCourse(intent.courseId)
            is CourseDetailIntent.LessonClicked -> navigateToLesson(intent.lessonId)
            is CourseDetailIntent.BackPressed -> sendEffect(CourseDetailEffect.NavigateBack)
        }
    }
    
    private fun loadCourse(courseId: String) {
        viewModelScope.launch {
            combine(
                getCourseByIdUseCase(courseId),
                getLessonsUseCase(courseId)
            ) { course, lessons ->
                setState {
                    copy(
                        isLoading = false,
                        course = course,
                        lessons = lessons
                    )
                }
            }.catch { e ->
                setState { copy(isLoading = false, error = e.message) }
            }.collect()
        }
    }
    
    private fun navigateToLesson(lessonId: String) {
        sendEffect(CourseDetailEffect.NavigateToLesson(lessonId))
    }
}