package com.yodgorbek.codelearnapp.presentation.tutor

import com.yodgorbek.codelearnapp.presentation.mvi.UiEffect
import com.yodgorbek.codelearnapp.presentation.mvi.UiIntent
import com.yodgorbek.codelearnapp.presentation.mvi.UiState
import java.util.UUID

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

data class AiTutorState(
    val lessonId: String? = null,
    val messages: List<ChatMessage> = emptyList(),
    val isLoading: Boolean = false,
    val inputText: String = ""
) : UiState

sealed class AiTutorIntent : UiIntent {
    data class InitLesson(val lessonId: String) : AiTutorIntent()
    data class SendMessage(val text: String) : AiTutorIntent()
    data class UpdateInput(val text: String) : AiTutorIntent()
    data object ClearChat : AiTutorIntent()
}

sealed class AiTutorEffect : UiEffect {
    data class ShowError(val message: String) : AiTutorEffect()
}
