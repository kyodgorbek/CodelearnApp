package com.example.codelearnapp.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class MviViewModel<Intent : UiIntent, State : UiState, Effect : UiEffect> : ViewModel() {
    
    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State
    
    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()
    
    private val _intent: MutableSharedFlow<Intent> = MutableSharedFlow()
    
    private val _effect: MutableSharedFlow<Effect> = MutableSharedFlow()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()
    
    init {
        subscribeIntents()
    }
    
    private fun subscribeIntents() {
        viewModelScope.launch {
            _intent.collect { intent ->
                handleIntent(intent)
            }
        }
    }
    
    abstract fun handleIntent(intent: Intent)
    
    fun sendIntent(intent: Intent) {
        viewModelScope.launch {
            _intent.emit(intent)
        }
    }
    
    protected fun setState(reduce: State.() -> State) {
        _state.value = _state.value.reduce()
    }
    
    protected fun sendEffect(effect: Effect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}