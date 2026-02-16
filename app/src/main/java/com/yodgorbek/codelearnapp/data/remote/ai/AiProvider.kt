package com.yodgorbek.codelearnapp.data.remote.ai

import com.yodgorbek.codelearnapp.presentation.tutor.ChatMessage

interface AiProvider {
    suspend fun generateResponse(
        systemInstruction: String,
        lessonContext: String,
        history: List<ChatMessage>,
        userMessage: String
    ): String
}
