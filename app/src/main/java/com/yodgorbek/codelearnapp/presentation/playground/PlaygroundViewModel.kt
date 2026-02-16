package com.yodgorbek.codelearnapp.presentation.playground

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yodgorbek.codelearnapp.domain.codeexecution.CodeExecutor
import com.yodgorbek.codelearnapp.domain.codeexecution.CodeExecutionResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class PlaygroundState(
    val code: String = "",
    val language: String = "Kotlin",
    val output: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class PlaygroundEffect {
    data class ShowError(val message: String) : PlaygroundEffect()
}

class PlaygroundViewModel(
    private val codeExecutor: CodeExecutor
) : ViewModel() {

    private val _state = MutableStateFlow(PlaygroundState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PlaygroundEffect>()
    val effect = _effect.asSharedFlow()

    init {
        updateCodeForLanguage("Kotlin")
    }

    fun onCodeChange(newCode: String) {
        _state.value = _state.value.copy(code = newCode)
    }

    fun onLanguageChange(newLanguage: String) {
        _state.value = _state.value.copy(language = newLanguage)
        updateCodeForLanguage(newLanguage)
    }

    private fun updateCodeForLanguage(language: String) {
        val defaultCode = when (language.lowercase()) {
            "kotlin" -> "fun main() {\n    println(\"Hello, Kotlin!\")\n}"
            "python" -> "print(\"Hello, Python!\")"
            "javascript" -> "console.log(\"Hello, JavaScript!\");"
            else -> ""
        }
        _state.value = _state.value.copy(code = defaultCode)
    }

    fun runCode() {
        val currentState = _state.value
        _state.value = currentState.copy(isLoading = true, output = "", error = null)

        viewModelScope.launch {
            val result = when (currentState.language.lowercase()) {
                "kotlin" -> codeExecutor.executeKotlinCode(currentState.code)
                "python" -> codeExecutor.executePythonCode(currentState.code)
                "javascript" -> codeExecutor.executeJavaScriptCode(currentState.code)
                else -> CodeExecutionResult.Error("Unsupported language")
            }

            when (result) {
                is CodeExecutionResult.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        output = result.output
                    )
                }
                is CodeExecutionResult.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                    _effect.emit(PlaygroundEffect.ShowError(result.message))
                }
            }
        }
    }
}
