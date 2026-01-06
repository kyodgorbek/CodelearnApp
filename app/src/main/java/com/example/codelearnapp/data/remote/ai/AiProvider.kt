package com.example.codelearnapp.data.remote.ai

import com.example.codelearnapp.presentation.tutor.ChatMessage

interface AiProvider {
    suspend fun generateResponse(
        systemInstruction: String,
        lessonContext: String,
        history: List<ChatMessage>,
        userMessage: String
    ): String
}
