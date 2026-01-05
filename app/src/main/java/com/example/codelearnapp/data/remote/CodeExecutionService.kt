package com.example.codelearnapp.data.remote

import com.example.codelearnapp.domain.model.CodeExecutionRequest
import com.example.codelearnapp.domain.model.CodeExecutionResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object CodeExecutionService {
    private const val BASE_URL = "http://10.0.2.2:3000" // Emulator localhost access

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
        install(Logging) {
            level = LogLevel.BODY
        }
    }

    suspend fun executeCode(request: CodeExecutionRequest): CodeExecutionResponse {
        return try {
            val response: HttpResponse = client.post("$BASE_URL/execute") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            response.body()
        } catch (e: Exception) {
            CodeExecutionResponse(
                output = null,
                error = "Network Error: ${e.message}"
            )
        }
    }
}
