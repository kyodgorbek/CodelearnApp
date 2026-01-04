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
                val variables = mutableMapOf<String, String>()
                
                code.lines().forEach { line ->
                    val trimmed = line.trim()
                    // Variable assignment: val x = "value" or val x = 10
                    if (trimmed.startsWith("val ") || trimmed.startsWith("var ")) {
                        val assignment = trimmed.substringAfter("val ").substringAfter("var ")
                        if (assignment.contains("=")) {
                            val name = assignment.substringBefore("=").trim().split(":").first().trim()
                            val value = assignment.substringAfter("=").trim().trim(';').trim('"')
                            variables[name] = value
                        }
                    }
                    
                    if (trimmed.contains("println(")) {
                        var content = trimmed.substringAfter("println(").substringBeforeLast(")")
                            .trim().trim('"').trim('\'')
                        
                        // Resolve simple variables: $name or ${name}
                        variables.forEach { (name, value) ->
                            content = content.replace("${'$'}$name", value)
                            content = content.replace("${'$'}{$name}", value)
                        }
                        
                        // If it's just a variable name by itself (not in a string)
                        if (variables.containsKey(content)) {
                            output.append(variables[content]).append("\n")
                        } else {
                            output.append(content).append("\n")
                        }
                    }
                }
                
                val finalOutput = output.toString()
                if (finalOutput.isEmpty()) {
                    CodeExecutionResult.Success("Code executed successfully")
                } else {
                    CodeExecutionResult.Success(finalOutput)
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
                val variables = mutableMapOf<String, String>()

                code.lines().forEach { line ->
                    val trimmed = line.trim()
                    
                    if (trimmed.contains("=") && !trimmed.startsWith("print") && !trimmed.startsWith("if")) {
                        val name = trimmed.substringBefore("=").trim()
                        val value = trimmed.substringAfter("=").trim().trim('"').trim('\'')
                        variables[name] = value
                    }

                    if (trimmed.startsWith("print(")) {
                        var content = trimmed.substringAfter("print(").substringBeforeLast(")")
                            .trim().trim('"').trim('\'')
                        
                        // Support for f-strings: f"{name}"
                        if (content.startsWith("f")) {
                            content = content.substring(1).trim('"').trim('\'')
                        }

                        // Resolve variables in {name}
                        variables.forEach { (name, value) ->
                            content = content.replace("{$name}", value)
                        }
                        
                        if (variables.containsKey(content)) {
                            output.append(variables[content]).append("\n")
                        } else {
                            output.append(content).append("\n")
                        }
                    }
                }
                CodeExecutionResult.Success(if (output.isEmpty()) "Script finished" else output.toString())
            } catch (e: Exception) {
                CodeExecutionResult.Error(e.message ?: "Python Execution failed")
            }
        }
    }
    
    suspend fun executeJavaScriptCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
            try {
                val output = StringBuilder()
                code.lines().forEach { line ->
                    if (line.trim().contains("console.log(")) {
                        val content = line.substringAfter("console.log(").substringBeforeLast(")")
                            .trim().trim('"').trim('\'')
                        output.append(content).append("\n")
                    }
                }
                CodeExecutionResult.Success(if (output.isEmpty()) "JS executed" else output.toString())
            } catch (e: Exception) {
                CodeExecutionResult.Error(e.message ?: "JS Execution failed")
            }
        }
    }

    suspend fun executeJavaCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
            try {
                val output = StringBuilder()
                val variables = mutableMapOf<String, String>()

                code.lines().forEach { line ->
                    val trimmed = line.trim()
                    
                    if (trimmed.contains("=") && (trimmed.startsWith("int ") || trimmed.startsWith("double ") || trimmed.startsWith("String ") || trimmed.startsWith("boolean "))) {
                        val namePart = trimmed.substringBefore("=").trim().split(" ").last()
                        val valuePart = trimmed.substringAfter("=").trim().trim(';').trim('"')
                        variables[namePart] = valuePart
                    }

                    if (trimmed.contains("System.out.println(")) {
                        var content = trimmed.substringAfter("System.out.println(").substringBeforeLast(")")
                            .trim().trim('"').trim('\'')
                        
                        // Basic concatenation support: "Hello " + name
                        if (content.contains("+")) {
                            val parts = content.split("+")
                            val resolvedPats = parts.map { part ->
                                val p = part.trim().trim('"').trim('\'')
                                variables[p] ?: p
                            }
                            output.append(resolvedPats.joinToString("")).append("\n")
                        } else if (variables.containsKey(content)) {
                            output.append(variables[content]).append("\n")
                        } else {
                            output.append(content).append("\n")
                        }
                    }
                }
                CodeExecutionResult.Success(if (output.isEmpty()) "Java execution finished" else output.toString())
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
