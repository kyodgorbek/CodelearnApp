package com.example.codelearnapp.data.remote.ai

import com.example.codelearnapp.BuildConfig
import com.example.codelearnapp.presentation.tutor.ChatMessage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class HfRequest(
    val inputs: String,
    val parameters: HfParameters = HfParameters()
)

@Serializable
data class HfParameters(
    val max_new_tokens: Int = 200,
    val return_full_text: Boolean = false,
    val temperature: Double = 0.3
)

@Serializable
data class HfResponse(
    val generated_text: String
)

class HuggingFaceAiProvider : AiProvider {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json { 
                ignoreUnknownKeys = true 
                isLenient = true
            })
        }
        install(Logging) {
            level = LogLevel.INFO
        }
    }

    // Updated Endpoint: using router.huggingface.co as api-inference is deprecated
    private val modelUrl = "https://router.huggingface.co/hf-inference/models/mistralai/Mistral-7B-Instruct-v0.2"

    private val jsonParser = Json { 
        ignoreUnknownKeys = true 
        isLenient = true 
    }

    override suspend fun generateResponse(
        systemInstruction: String,
        lessonContext: String,
        history: List<ChatMessage>,
        userMessage: String
    ): String {
        // Construct the prompt using Mistral's [INST] format
        val prompt = buildMistralPrompt(systemInstruction, lessonContext, history, userMessage)

        try {
            // Fetch response as raw text first to handle dynamic JSON types (Object vs Array)
            val responseString = client.post(modelUrl) {
                header("Authorization", "Bearer ${BuildConfig.HF_API_KEY}")
                contentType(ContentType.Application.Json)
                setBody(HfRequest(inputs = prompt))
            }.bodyAsText()

            // Parse to generic JsonElement
            val element = jsonParser.parseToJsonElement(responseString)

            return when (element) {
                is JsonArray -> {
                    // Success case: Array of HfResponse
                    val responses = jsonParser.decodeFromJsonElement<List<HfResponse>>(element)
                    responses.firstOrNull()?.generated_text?.trim() ?: "No response from AI."
                }
                is JsonObject -> {
                    // Error case: Object with "error" field
                    if (element.containsKey("error")) {
                        val errorMsg = element["error"]?.jsonPrimitive?.content ?: "Unknown error"
                        
                        // Handle "Model is loading" state specifically
                        if (errorMsg.contains("loading", ignoreCase = true)) {
                            "The AI model is currently waking up. Please try again in about 20 seconds."
                        } else {
                            "AI Service Error: $errorMsg"
                        }
                    } else {
                        "Received an unexpected response format from the AI provider."
                    }
                }
                else -> "Unknown response format."
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return "Connection Error: ${e.message}"
        }
    }

    private fun buildMistralPrompt(
        systemInstruction: String,
        lessonContext: String,
        history: List<ChatMessage>,
        userMessage: String
    ): String {
        val sb = StringBuilder()
        
        // Structure:
        // <s>[INST] System + Context + User [/INST] Model </s>[INST] User [/INST] ...
        
        // Combine System Prompt and Lesson Context into the "System" block
        val contextBlock = """
            $systemInstruction
            
            LESSON CONTEXT
            $lessonContext
        """.trimIndent()
        
        if (history.isEmpty()) {
            // First turn
            sb.append("<s>[INST] $contextBlock\n\nUSER INPUT\nQuestion:\n$userMessage [/INST]")
        } else {
            // Multi-turn history reconstruction
            sb.append("<s>")
            
            var isFirstUserMsg = true
            history.forEach { msg ->
                if (msg.isUser) {
                    sb.append("[INST] ")
                    if (isFirstUserMsg) {
                        // Inject context into the first user message
                        sb.append("$contextBlock\n\nUSER INPUT\nQuestion:\n")
                        isFirstUserMsg = false
                    }
                    sb.append(msg.text)
                    sb.append(" [/INST] ")
                } else {
                    sb.append(msg.text)
                    sb.append("</s>")
                }
            }
            
            // Append current user message
            sb.append("[INST] ")
            if (isFirstUserMsg) {
                 // Fallback if history had no user messages (unlikely)
                 sb.append("$contextBlock\n\nUSER INPUT\nQuestion:\n")
            }
            sb.append(userMessage)
            sb.append(" [/INST]")
        }
        
        return sb.toString()
    }
}
