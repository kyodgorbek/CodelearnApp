package com.example.codelearnapp.domain.repository

import com.example.codelearnapp.presentation.tutor.ChatMessage

interface AiRepository {
    suspend fun getAiResponse(
        lessonId: String,
        userMessage: String,
        history: List<ChatMessage>
    ): String
    
    suspend fun getInitialGreeting(lessonId: String): String
    
    fun getMessages(lessonId: String): kotlinx.coroutines.flow.Flow<List<ChatMessage>>
}
