package com.example.codelearnapp.domain.codeexecution

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CodeExecutor {
    
    suspend fun executeKotlinCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
            try {
                // For a real app, this is very complex on Android.
                // We'll simulate output redirection for the demo.
                val output = StringBuilder()
                val mockSystemOut = object {
                    fun println(msg: Any?) { output.append(msg.toString()).append("\n") }
                    fun print(msg: Any?) { output.append(msg.toString()) }
                }
                
                // Very basic simulation of running the code
                // In a real app we'd use the Kotlin compiler scripting API
                val result = if (code.contains("println")) {
                    val lines = code.lines()
                    lines.forEach { line ->
                        if (line.trim().startsWith("println(")) {
                            val content = line.substringAfter("println(").substringBeforeLast(")")
                                .trim().trim('"').trim('\'')
                            output.append(content).append("\n")
                        }
                    }
                    CodeExecutionResult.Success(output.toString())
                } else {
                    CodeExecutionResult.Success("Code executed successfully (simulated)")
                }
                result
            } catch (e: Exception) {
                CodeExecutionResult.Error(e.message ?: "Execution failed")
            }
        }
    }
    
    suspend fun executePythonCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
            try {
                // Simulate Python output
                val output = StringBuilder()
                if (code.contains("print(")) {
                    val lines = code.lines()
                    lines.forEach { line ->
                        if (line.trim().startsWith("print(")) {
                            val content = line.substringAfter("print(").substringBeforeLast(")")
                                .trim().trim('"').trim('\'')
                            output.append(content).append("\n")
                        }
                    }
                    CodeExecutionResult.Success(output.toString())
                } else {
                    CodeExecutionResult.Success("Python code executed successfully (simulated)")
                }
            } catch (e: Exception) {
                CodeExecutionResult.Error(e.message ?: "Execution failed")
            }
        }
    }
    
    suspend fun executeJavaScriptCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
            try {
                val output = StringBuilder()
                // basic JS simulation
                if (code.contains("console.log(")) {
                    val lines = code.lines()
                    lines.forEach { line ->
                        if (line.trim().contains("console.log(")) {
                            val content = line.substringAfter("console.log(").substringBeforeLast(")")
                                .trim().trim('"').trim('\'')
                            output.append(content).append("\n")
                        }
                    }
                    CodeExecutionResult.Success(output.toString())
                } else {
                    CodeExecutionResult.Success("JavaScript code executed successfully (simulated)")
                }
            } catch (e: Exception) {
                CodeExecutionResult.Error(e.message ?: "Execution failed")
            }
        }
    }
}

sealed class CodeExecutionResult {
    data class Success(val output: String) : CodeExecutionResult()
    data class Error(val message: String) : CodeExecutionResult()
}
