package com.example.codelearnapp.data.repository

import com.example.codelearnapp.BuildConfig
import com.example.codelearnapp.data.AiTutorPrompts
import com.example.codelearnapp.data.local.dao.ChatDao
import com.example.codelearnapp.data.local.entity.ChatEntity
import com.example.codelearnapp.domain.repository.AiRepository
import com.example.codelearnapp.presentation.tutor.ChatMessage
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AiRepositoryImpl(
    private val chatDao: ChatDao,
    private val aiProvider: com.example.codelearnapp.data.remote.ai.AiProvider
) : AiRepository {

    // Removed direct Gemini usage, now delegated to aiProvider


    override suspend fun getInitialGreeting(lessonId: String): String {
        return "Hello! I'm your AI Tutor for this lesson. I'm here to help you master this concept step-by-step. Ask me anything!"
    }

    override fun getMessages(lessonId: String): Flow<List<ChatMessage>> {
        return chatDao.getMessagesForLesson(lessonId).map { entities ->
            entities.map { entity ->
                ChatMessage(
                    id = entity.id,
                    text = entity.text,
                    isUser = entity.isUser,
                    timestamp = entity.timestamp
                )
            }
        }
    }

    override suspend fun getAiResponse(
        lessonId: String,
        userMessage: String,
        history: List<ChatMessage>
    ): String {
        // Save user message
        chatDao.insertMessage(
            ChatEntity(
                lessonId = lessonId,
                text = userMessage,
                isUser = true
            )
        )

        // Special Logic for Hints (local override to save cost/latency, and ensure structured help)
        // Check if user specifically asks for hint
        if (userMessage.contains("hint", ignoreCase = true)) {
            val responseText = handleHintRequest(lessonId, history)
            saveAiResponse(lessonId, responseText)
            return responseText
        }

        try {
            // 1. Prepare Context
            val lessonPrompt = AiTutorPrompts.LESSON_PROMPTS[lessonId] 
                ?: "You are a helpful AI tutor for Computer Science."
            val systemInstruction = AiTutorPrompts.SYSTEM_INSTRUCTION
            
            // 2. Delegate to Provider
            val responseText = aiProvider.generateResponse(
                systemInstruction = systemInstruction,
                lessonContext = lessonPrompt,
                history = history,
                userMessage = userMessage
            )
            
            saveAiResponse(lessonId, responseText)
            return responseText

        } catch (e: Exception) {
            e.printStackTrace()
            // Fallback to simulated response on error
            val fallback = "I'm having trouble connecting to the network right now. \n\nError: ${e.message}"
            saveAiResponse(lessonId, fallback)
            return fallback
        }
    }

    private suspend fun saveAiResponse(lessonId: String, text: String) {
        chatDao.insertMessage(
            ChatEntity(
                lessonId = lessonId,
                text = text,
                isUser = false
            )
        )
    }

    private fun handleHintRequest(lessonId: String, history: List<ChatMessage>): String {
        // Count how many hints have been given so far in this session
        val hintCount = history.count { !it.isUser && it.text.contains("Hint", ignoreCase = true) }
        
        return when(hintCount) {
            0 -> "Hint 1: Let's break the problem down. What is the first step you think we should take?"
            1 -> "Hint 2: Remember that in Kotlin, we often use specific keywords for this. Have you checked the lesson text?"
            else -> "Hint 3: Try writing a small piece of code that prints the output. That often helps visualize the problem."
        }
    }
}
