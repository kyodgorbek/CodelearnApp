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
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class HfRequest(
    val inputs: String,
    val parameters: HfParameters = HfParameters()
)

@Serializable
data class HfParameters(
    val max_new_tokens: Int = 500,
    val return_full_text: Boolean = false,
    val temperature: Double = 0.7
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

    // Using Mistral 7B Instruct which is very capable and fast
    private val modelUrl = "https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.2"

    override suspend fun generateResponse(
        systemInstruction: String,
        lessonContext: String,
        history: List<ChatMessage>,
        userMessage: String
    ): String {
        // Construct the prompt using Mistral's [INST] format
        val prompt = buildMistralPrompt(systemInstruction, lessonContext, history, userMessage)

        try {
            val response: List<HfResponse> = client.post(modelUrl) {
                header("Authorization", "Bearer ${BuildConfig.HF_API_KEY}")
                contentType(ContentType.Application.Json)
                setBody(HfRequest(inputs = prompt))
            }.body()

            return response.firstOrNull()?.generated_text?.trim() ?: "No response from AI."
        } catch (e: Exception) {
            e.printStackTrace()
            return "Error contacting AI: ${e.message}"
        }
    }

    private fun buildMistralPrompt(
        systemInstruction: String,
        lessonContext: String,
        history: List<ChatMessage>,
        userMessage: String
    ): String {
        val sb = StringBuilder()
        
        // System & Context (Mistral Instruct doesn't strictly have a "System" role in the v0.2 raw format, 
        // usually it's prepend to the first instruction or treated as such)
        val initialContext = "$systemInstruction\n\nCONTEXT: $lessonContext"
        
        // If history is empty, just send fresh
        if (history.isEmpty()) {
            sb.append("<s>[INST] $initialContext\n\n$userMessage [/INST]")
        } else {
            // Reconstruct history
            // Mistral format: <s>[INST] Instruction [/INST] Model Answer</s>[INST] Follow-up [/INST]
            sb.append("<s>")
            
            // Add initial context to the VERY FIRST user message if possible
            // We'll iterate and rebuild.
            // Simplified approach: Just append context to the beginning of the current prompt chain?
            // No, context needs to be "remembered".
            
            // NOTE: HF Inference API is stateless. We must send full history.
            
            var isFirstUserMsg = true
            history.forEach { msg ->
                if (msg.isUser) {
                    sb.append("[INST] ")
                    if (isFirstUserMsg) {
                        sb.append("$initialContext\n\n")
                        isFirstUserMsg = false
                    }
                    sb.append(msg.text)
                    sb.append(" [/INST] ")
                } else {
                    sb.append(msg.text)
                    sb.append("</s>")
                }
            }
            
            // Add current message
            sb.append("[INST] ")
            if (isFirstUserMsg) { // If history was somehow only AI messages or empty (unlikely handled above)
                 sb.append("$initialContext\n\n")
            }
            sb.append(userMessage)
            sb.append(" [/INST]")
        }
        
        return sb.toString()
    }
}
