package com.example.codelearnapp.presentation.search

import androidx.lifecycle.viewModelScope
import com.example.codelearnapp.domain.usecase.SearchLessonsUseCase
import com.example.codelearnapp.presentation.mvi.MviViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchLessonsUseCase: SearchLessonsUseCase
) : MviViewModel<SearchIntent, SearchState, SearchEffect>() {
    
    override fun createInitialState(): SearchState = SearchState()
    
    override fun handleIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.Search -> searchLessons(intent.query)
            is SearchIntent.ClearSearch -> clearSearch()
            is SearchIntent.LessonClicked -> navigateToLesson(intent.lessonId)
        }
    }
    
    private fun searchLessons(query: String) {
        if (query.isEmpty()) {
            setState { copy(query = "", results = emptyList()) }
            return
        }
        
        setState { copy(isLoading = true, query = query) }
        
        viewModelScope.launch {
            searchLessonsUseCase(query)
                .catch { e ->
                    setState { copy(isLoading = false, results = emptyList()) }
                }
                .collect { lessons ->
                    setState {
                        copy(
                            isLoading = false,
                            results = lessons
                        )
                    }
                }
        }
    }
    
    private fun clearSearch() {
        setState { copy(query = "", results = emptyList()) }
    }
    
    private fun navigateToLesson(lessonId: String) {
        sendEffect(SearchEffect.NavigateToLesson(lessonId))
    }
}
