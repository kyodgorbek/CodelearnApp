package com.example.codelearnapp.domain.codeexecution

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
//import javax.script.ScriptEngineManager

class CodeExecutor {
    
    suspend fun executeKotlinCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
            try {
                // This is a simplified version
                // For production, use kotlin-compiler-embeddable
                val output = StringBuilder()
                
                // Redirect print statements
                val modifiedCode = code.replace(
                    "println(",
                    "output.append("
                ).replace(")", ").append(\"\\n\")")
                
                CodeExecutionResult.Success(output.toString())
            } catch (e: Exception) {
                CodeExecutionResult.Error(e.message ?: "Execution failed")
            }
        }
    }
    
    suspend fun executePythonCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
            try {
                // Note: This requires Python runtime on device
                // For production, use Chaquopy or similar
                CodeExecutionResult.Success("Python execution not available in this demo")
            } catch (e: Exception) {
                CodeExecutionResult.Error(e.message ?: "Execution failed")
            }
        }
    }
    
    suspend fun executeJavaScriptCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
            try {
            //    val engine = //ScriptEngineManager().getEngineByName("rhino")
                 //   ?: return@withContext CodeExecutionResult.Error("JavaScript engine not available")
                
                // Capture console.log
                val output = StringBuilder()
               // engine.put("output", output)
                
                val wrappedCode = """
                    var console = { 
                        log: function(msg) { 
                            output.append(msg + "\n"); 
                        } 
                    };
                    $code
                """.trimIndent()
                
              //  engine.eval(wrappedCode)
                CodeExecutionResult.Success(output.toString())
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
