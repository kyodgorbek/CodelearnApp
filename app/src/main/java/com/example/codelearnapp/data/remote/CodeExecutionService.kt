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
    private const val PISTON_URL = "https://emkc.org/api/v2/piston"

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
                encodeDefaults = true
            })
        }
        install(Logging) {
            level = LogLevel.BODY
        }
    }

    suspend fun executeCode(request: CodeExecutionRequest): CodeExecutionResponse {
        return try {
            val languageConfig = getLanguageConfig(request.language)
                ?: return CodeExecutionResponse(output = null, error = "Unsupported language: ${request.language}")

            val pistonRequest = PistonRequest(
                language = languageConfig.language,
                version = languageConfig.version,
                files = listOf(PistonFile(content = request.script))
            )

            val response: PistonResponse = client.post("$PISTON_URL/execute") {
                contentType(ContentType.Application.Json)
                setBody(pistonRequest)
            }.body()

            mapPistonResponse(response)

        } catch (e: Exception) {
            e.printStackTrace()
            CodeExecutionResponse(
                output = null,
                error = "Execution Error: ${e.message}"
            )
        }
    }

    private fun getLanguageConfig(language: String): LanguageConfig? {
        return when (language.lowercase()) {
            "kotlin" -> LanguageConfig("kotlin", "1.8.20")
            "java" -> LanguageConfig("java", "15.0.2")
            "python", "python3" -> LanguageConfig("python", "3.10.0")
            "javascript", "nodejs", "js" -> LanguageConfig("javascript", "18.15.0")
            "sql", "sqlite" -> LanguageConfig("sqlite3", "3.36.0")
            else -> null
        }
    }

    private fun mapPistonResponse(pistonResponse: PistonResponse): CodeExecutionResponse {
        val run = pistonResponse.run
        val compile = pistonResponse.compile

        val outputBuilder = StringBuilder()
        
        // Append compile output if it exists (usually errors)
        if (compile != null) {
            if (!compile.stdout.isNullOrEmpty()) outputBuilder.append(compile.stdout).append("\n")
            if (!compile.stderr.isNullOrEmpty()) outputBuilder.append(compile.stderr).append("\n")
            if (!compile.output.isNullOrEmpty() && compile.output != compile.stdout && compile.output != compile.stderr) {
                outputBuilder.append(compile.output).append("\n")
            }
        }

        // Append run output
        if (run != null) {
            if (!run.output.isNullOrEmpty()) {
                outputBuilder.append(run.output)
            } else {
                // Fallback if 'output' is strictly combined but might be missing in some error cases
                if (!run.stdout.isNullOrEmpty()) outputBuilder.append(run.stdout)
                if (!run.stderr.isNullOrEmpty()) outputBuilder.append(run.stderr)
            }
        }

        val memory = run?.memory?.toString() ?: "0"
        // CPU time is usually in nanoseconds or milliseconds depending on platform, Piston sends ms? 
        // Piston docs say: compile_cpu_time (ms). We convert to string seconds for display if needed or keep as is.
        // Server.js was doing / 1000. Let's do the same calculation here if it's ms.
        // Wait, app expects "cpuTime". Server.js: (run.cpu_time / 1000).toString().
        // Piston sends raw numbers.
        val cpuTimeVal = run?.cpu_time ?: 0.0
        val cpuTimeStr = (cpuTimeVal / 1000.0).toString() 

        // We return success even if code failed (exit code != 0), so user sees the error output.
        // statusCode 200 implies "Request successful", actual run code is inside run.code
        return CodeExecutionResponse(
            output = outputBuilder.toString().trim(),
            statusCode = run?.code ?: 0, 
            memory = memory,
            cpuTime = cpuTimeStr
        )
    }

    private data class LanguageConfig(val language: String, val version: String)

    @kotlinx.serialization.Serializable
    private data class PistonRequest(
        val language: String,
        val version: String,
        val files: List<PistonFile>
    )

    @kotlinx.serialization.Serializable
    private data class PistonFile(
        val content: String
    )

    @kotlinx.serialization.Serializable
    private data class PistonResponse(
        val language: String? = null,
        val version: String? = null,
        val run: PistonStage? = null,
        val compile: PistonStage? = null
    )

    @kotlinx.serialization.Serializable
    private data class PistonStage(
        val stdout: String? = null,
        val stderr: String? = null,
        val output: String? = null,
        val code: Int? = null,
        val signal: String? = null,
        val memory: Long? = null,
        val cpu_time: Double? = null 
    )
}
