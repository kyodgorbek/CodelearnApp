package com.example.codelearnapp.data.remote.ai

import com.example.codelearnapp.BuildConfig
import com.example.codelearnapp.presentation.tutor.ChatMessage
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig

class GeminiAiProvider : AiProvider {

    private val generativeModel by lazy {
        GenerativeModel(
            modelName = "gemini-pro",
            apiKey = BuildConfig.GEMINI_API_KEY,
            generationConfig = generationConfig {
                temperature = 0.7f
                topK = 40
                topP = 0.95f
                maxOutputTokens = 1000
            }
        )
    }

    override suspend fun generateResponse(
        systemInstruction: String,
        lessonContext: String,
        history: List<ChatMessage>,
        userMessage: String
    ): String {
        // Gemini doesn't fully support system_instruction in the exact same way across all versions of this specific SDK wrapper instantly,
        // so we stick to the Prompt-based context injection or rely on startChat.
        
        // Construct the chat history for Gemini SDK
        val chat = generativeModel.startChat(
            history = history.map { msg ->
                content(if (msg.isUser) "user" else "model") { text(msg.text) }
            }
        )

        val fullPrompt = """
            $systemInstruction
            
            LESSON CONTEXT:
            $lessonContext
            
            STUDENT QUESTION:
            $userMessage
        """.trimIndent()

        val response = chat.sendMessage(fullPrompt)
        return response.text ?: "I'm not sure how to answer that."
    }
}
