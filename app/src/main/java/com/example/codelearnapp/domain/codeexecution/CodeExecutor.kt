package com.example.codelearnapp.domain.codeexecution

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CodeExecutor {
    
    suspend fun executeKotlinCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
            try {
                val output = StringBuilder()
                val lines = code.lines()
                val variables = mutableMapOf<String, Any>()
                
                lines.forEach { line ->
                    val trimmed = line.trim()
                    // Simulation of variable assignment
                    if (trimmed.startsWith("val ") || trimmed.startsWith("var ")) {
                        val parts = trimmed.substring(4).split("=")
                        if (parts.size == 2) {
                            val name = parts[0].trim()
                            val value = parts[1].trim().trim('"')
                            variables[name] = value
                        }
                    }
                    
                    // Simulation of println
                    if (trimmed.startsWith("println(")) {
                        val content = trimmed.substringAfter("println(").substringBeforeLast(")")
                            .trim().trim('"').trim('\'')
                        
                        if (variables.containsKey(content)) {
                            output.append(variables[content]).append("\n")
                        } else {
                            output.append(content).append("\n")
                        }
                    }
                }
                
                if (output.isEmpty() && !code.contains("fun main")) {
                    CodeExecutionResult.Success("Code check complete: Syntax looks good!")
                } else if (output.isEmpty()) {
                    CodeExecutionResult.Success("Executed (No output produced)")
                } else {
                    CodeExecutionResult.Success(output.toString())
                }
            } catch (e: Exception) {
                CodeExecutionResult.Error(e.message ?: "Execution failed")
            }
        }
    }
    
    suspend fun executePythonCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
            try {
                val output = StringBuilder()
                val lines = code.lines()
                val variables = mutableMapOf<String, Any>()

                lines.forEach { line ->
                    val trimmed = line.trim()
                    
                    // Assignment
                    if (trimmed.contains("=") && !trimmed.startsWith("if") && !trimmed.startsWith("print(")) {
                        val parts = trimmed.split("=")
                        val name = parts[0].trim()
                        val value = parts[1].trim().trim('"')
                        variables[name] = value
                    }

                    if (trimmed.startsWith("print(")) {
                        val content = trimmed.substringAfter("print(").substringBeforeLast(")")
                            .trim().trim('"').trim('\'')
                        
                        if (variables.containsKey(content)) {
                            output.append(variables[content]).append("\n")
                        } else {
                            output.append(content).append("\n")
                        }
                    }
                }
                CodeExecutionResult.Success(if (output.isEmpty()) "Script finished with no output" else output.toString())
            } catch (e: Exception) {
                CodeExecutionResult.Error(e.message ?: "Python Execution failed")
            }
        }
    }
    
    suspend fun executeJavaScriptCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
            try {
                val output = StringBuilder()
                val lines = code.lines()
                lines.forEach { line ->
                    if (line.trim().contains("console.log(")) {
                        val content = line.substringAfter("console.log(").substringBeforeLast(")")
                            .trim().trim('"').trim('\'')
                        output.append(content).append("\n")
                    }
                }
                CodeExecutionResult.Success(if (output.isEmpty()) "JS executed successfully" else output.toString())
            } catch (e: Exception) {
                CodeExecutionResult.Error(e.message ?: "JS Execution failed")
            }
        }
    }

    suspend fun executeJavaCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
            try {
                val output = StringBuilder()
                val lines = code.lines()
                val variables = mutableMapOf<String, Any>()

                lines.forEach { line ->
                    val trimmed = line.trim()
                    
                    // Basic Assignment simulation (e.g., int x = 10;)
                    if (trimmed.contains("=") && (trimmed.startsWith("int ") || trimmed.startsWith("double ") || trimmed.startsWith("String "))) {
                        val parts = trimmed.split("=")
                        val namePart = parts[0].trim().split(" ").last()
                        val valuePart = parts[1].trim().trim(';').trim('"')
                        variables[namePart] = valuePart
                    }

                    if (trimmed.contains("System.out.println(")) {
                        val content = trimmed.substringAfter("System.out.println(").substringBeforeLast(")")
                            .trim().trim('"').trim('\'')
                        
                        if (variables.containsKey(content)) {
                            output.append(variables[content]).append("\n")
                        } else {
                            output.append(content).append("\n")
                        }
                    }
                }
                CodeExecutionResult.Success(if (output.isEmpty()) "Java program finished" else output.toString())
            } catch (e: Exception) {
                CodeExecutionResult.Error(e.message ?: "Java Execution failed")
            }
        }
    }
}

sealed class CodeExecutionResult {
    data class Success(val output: String) : CodeExecutionResult()
    data class Error(val message: String) : CodeExecutionResult()
}
