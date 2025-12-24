package com.example.codelearnapp.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.codelearnapp.domain.usecase.GetCoursesUseCase
import com.example.codelearnapp.presentation.mvi.MviViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCoursesUseCase: GetCoursesUseCase
) : MviViewModel<HomeIntent, HomeState, HomeEffect>() {
    
    override fun createInitialState(): HomeState = HomeState()
    
    init {
        sendIntent(HomeIntent.LoadCourses)
    }
    
    override fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadCourses -> loadCourses()
            is HomeIntent.CourseClicked -> navigateToCourse(intent.courseId)
        }
    }
    
    private fun loadCourses() {
        viewModelScope.launch {
            getCoursesUseCase()
                .catch { e ->
                    setState { copy(isLoading = false, error = e.message) }
                    sendEffect(HomeEffect.ShowError(e.message ?: "Unknown error"))
                }
                .collect { courses ->
                    setState {
                        copy(
                            isLoading = false,
                            courses = courses,
                            error = null
                        )
                    }
                }
        }
    }
    
    private fun navigateToCourse(courseId: String) {
        sendEffect(HomeEffect.NavigateToCourse(courseId))
    }
}