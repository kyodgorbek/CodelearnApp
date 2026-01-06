package com.example.codelearnapp.data.remote.ai

import com.example.codelearnapp.presentation.tutor.ChatMessage
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import com.example.codelearnapp.BuildConfig



/* =========================
   REQUEST MODELS
   ========================= */

@Serializable
data class ChatCompletionRequest(
    val model: String,
    val messages: List<ChatCompletionMessage>,
    val temperature: Double = 0.3,
    val max_tokens: Int = 300
)

@Serializable
data class ChatCompletionMessage(
    val role: String, // system | user | assistant
    val content: String
)

/* =========================
   RESPONSE MODELS (SAFE)
   ========================= */

@Serializable
data class ChatCompletionResponse(
    val choices: List<Choice>? = null,
    val error: GroqError? = null
)

@Serializable
data class Choice(
    val message: ChatCompletionMessage
)

@Serializable
data class GroqError(
    val message: String? = null,
    val type: String? = null,
    val code: String? = null
)

/* =========================
   PROVIDER IMPLEMENTATION
   ========================= */

class GroqAiProvider : AiProvider {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
        install(Logging) {
            level = LogLevel.INFO
        }
    }

    private val endpoint =
        "https://api.groq.com/openai/v1/chat/completions"

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    override suspend fun generateResponse(
        systemInstruction: String,
        lessonContext: String,
        history: List<ChatMessage>,
        userMessage: String
    ): String {

        // 🔴 PUT YOUR REAL GROQ API KEY HERE
        val apiKey = BuildConfig.GROQ_API_KEY.trim()

        if (apiKey.length < 20) {
            return "Config Error: Invalid Groq API key"
        }

        val messages = buildMessages(
            systemInstruction,
            lessonContext,
            history,
            userMessage
        )

        val requestBody = ChatCompletionRequest(
            model = "llama-3.1-8b-instant", // ✅ CURRENT SUPPORTED MODEL
            messages = messages,
            temperature = 0.3,
            max_tokens = 300
        )

        return try {
            val responseText = client.post(endpoint) {
                header("Authorization", "Bearer $apiKey")
                contentType(ContentType.Application.Json)
                setBody(requestBody)
            }.bodyAsText()

            val root = json.parseToJsonElement(responseText)

            // Handle Groq error responses
            if (root is JsonObject && root.containsKey("error")) {
                val errorObj = root["error"]?.jsonObject
                val message = errorObj
                    ?.get("message")
                    ?.jsonPrimitive
                    ?.content
                    ?: "Unknown Groq error"

                return "AI Error: $message"
            }

            val parsed =
                json.decodeFromJsonElement(
                    ChatCompletionResponse.serializer(),
                    root
                )

            parsed.choices
                ?.firstOrNull()
                ?.message
                ?.content
                ?.trim()
                ?: "AI Error: Empty response"

        } catch (e: Exception) {
            e.printStackTrace()
            "Connection Error: ${e.message}"
        }
    }

    /* =========================
       MESSAGE BUILDER
       ========================= */

    private fun buildMessages(
        systemInstruction: String,
        lessonContext: String,
        history: List<ChatMessage>,
        userMessage: String
    ): List<ChatCompletionMessage> {

        val messages = mutableListOf<ChatCompletionMessage>()

        // System prompt
        messages += ChatCompletionMessage(
            role = "system",
            content = """
                $systemInstruction

                LESSON CONTEXT:
                $lessonContext
            """.trimIndent()
        )

        // Chat history
        history.forEach { msg ->
            messages += ChatCompletionMessage(
                role = if (msg.isUser) "user" else "assistant",
                content = msg.text
            )
        }

        // Current user message
        messages += ChatCompletionMessage(
            role = "user",
            content = userMessage
        )

        return messages
    }
}
