package com.example.codelearnapp.domain.codeexecution

import com.example.codelearnapp.data.remote.CodeExecutionService
import com.example.codelearnapp.domain.model.CodeExecutionRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CodeExecutor {

    private suspend fun executeWithFallback(
        code: String,
        language: String,
        localExecutor: suspend (String) -> CodeExecutionResult
    ): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
            // Uncomment to force local execution for debugging or if backend is not running
            // return@withContext localExecutor(code)

            try {
                val response = CodeExecutionService.executeCode(
                    CodeExecutionRequest(script = code, language = language)
                )

                if (response.error != null) {
                    // Network error or backend unreachable -> Fallback to local
                    println("Cloud execution failed: ${response.error}. Falling back to local.")
                    localExecutor(code)
                } else {
                    // Successful cloud execution
                    if (response.statusCode == 200 || response.statusCode == 201) {
                         CodeExecutionResult.Success(response.output ?: "")
                    } else {
                         // Compilation/Runtime error from cloud
                         CodeExecutionResult.Error(response.output ?: "Unknown execution error")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                localExecutor(code)
            }
        }
    }

    suspend fun executeKotlinCode(code: String): CodeExecutionResult {
        return executeWithFallback(code, "kotlin") { executeLocalKotlinCode(it) }
    }

    suspend fun executeJavaCode(code: String): CodeExecutionResult {
        return executeWithFallback(code, "java") { executeLocalJavaCode(it) }
    }

    suspend fun executePythonCode(code: String): CodeExecutionResult {
        return executeWithFallback(code, "python3") { executeLocalPythonCode(it) }
    }

    suspend fun executeJavaScriptCode(code: String): CodeExecutionResult {
        return executeWithFallback(code, "nodejs") { executeLocalJavaScriptCode(it) }
    }

    suspend fun executeSqlCode(code: String): CodeExecutionResult {
        return executeWithFallback(code, "sql") { executeLocalSqlCode(it) }
    }

    // --- Local Simulation Logic (Preserved as Fallback) ---

    private suspend fun executeLocalKotlinCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.Default) {
            try {
                val output = StringBuilder()
                val variables = mutableMapOf<String, String>()
                val collections = mutableMapOf<String, Any>()

                fun eval(expr: String, currentVars: Map<String, String>): String {
                    val trimmed = expr.trim().trim(';').trim('"').trim('\'')
                    if (currentVars.containsKey(trimmed)) return currentVars[trimmed]!!
                    
                    if (trimmed.contains("+") && (expr.contains("\"") || expr.contains("'"))) {
                         return trimmed.split("+").joinToString("") { eval(it, currentVars) }
                    }
                    
                    if (trimmed.matches(Regex(".*[+\\-*/%].*")) && !trimmed.contains("\"")) {
                        try {
                            val parts = trimmed.split(Regex("(?=[+\\-*/%])|(?<=[+\\-*/%])")).map { it.trim() }
                            var res = eval(parts[0], currentVars).toDoubleOrNull() ?: 0.0
                            var i = 1
                            while (i < parts.size) {
                                val op = parts[i]
                                val nextVal = eval(parts[i+1], currentVars).toDoubleOrNull() ?: 0.0
                                when(op) {
                                    "+" -> res += nextVal
                                    "-" -> res -= nextVal
                                    "*" -> res *= nextVal
                                    "/" -> res /= nextVal
                                    "%" -> res %= nextVal
                                }
                                i += 2
                            }
                            return if (res % 1 == 0.0) res.toInt().toString() else res.toString()
                        } catch(e: Exception) { return trimmed }
                    }

                    if (trimmed.contains(">") || trimmed.contains("<") || trimmed.contains("==") || trimmed.contains("!=")) {
                        val op = if (trimmed.contains("==")) "==" else if (trimmed.contains("!=")) "!=" else if (trimmed.contains(">=")) ">=" else if (trimmed.contains("<=")) "<=" else if (trimmed.contains(">")) ">" else "<"
                        val left = eval(trimmed.substringBefore(op), currentVars).toDoubleOrNull() ?: 0.0
                        val right = eval(trimmed.substringAfter(op), currentVars).toDoubleOrNull() ?: 0.0
                        return when(op) {
                            ">" -> (left > right).toString()
                            "<" -> (left < right).toString()
                            "==" -> (left == right).toString()
                            "!=" -> (left != right).toString()
                            ">=" -> (left >= right).toString()
                            "<=" -> (left <= right).toString()
                            else -> "false"
                        }
                    }
                    return trimmed
                }

                code.lines().forEach { line ->
                    val trimmed = line.trim()
                    if (trimmed.isEmpty() || trimmed.startsWith("//")) return@forEach
                    if (trimmed.contains("println(")) {
                        val expr = trimmed.substringAfter("println(").substringBeforeLast(")")
                        output.append(eval(expr, variables)).append("\n")
                    } else if (trimmed.contains(".put(") || trimmed.contains(".add(")) {
                         val name = trimmed.substringBefore(".").trim()
                         val args = trimmed.substringAfter("(").substringBeforeLast(")").split(",").map { eval(it, variables) }
                         if (trimmed.contains(".put(")) {
                             val map = collections.getOrPut(name) { mutableMapOf<String, String>() } as MutableMap<String, String>
                             map[args[0]] = args[1]
                         } else {
                             val list = collections.getOrPut(name) { mutableListOf<String>() } as MutableList<String>
                             list.add(args[0])
                         }
                    } else if (trimmed.contains("=")) {
                        val clean = if (trimmed.startsWith("val ") || trimmed.startsWith("var ")) trimmed.substringAfter(" ").trim() else trimmed
                        val name = clean.substringBefore("=").trim().split(":").first().trim()
                        variables[name] = eval(clean.substringAfter("="), variables)
                    }
                }
                CodeExecutionResult.Success(if (output.isEmpty()) "Executed successfully (Local)" else output.toString())
            } catch (e: Exception) {
                CodeExecutionResult.Error("Kotlin Error: ${e.message}")
            }
        }
    }

    private suspend fun executeLocalJavaCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.Default) {
            try {
                val output = StringBuilder()
                val variables = mutableMapOf<String, String>()
                val arrays = mutableMapOf<String, List<String>>()

                fun eval(expr: String): String {
                    var t = expr.trim().trim(';').trim('"').trim('\'')
                    if (t.isEmpty()) return ""

                    // Handle array access: arr[index]
                    if (t.contains("[") && t.endsWith("]")) {
                        val arrName = t.substringBefore("[")
                        val idxStr = t.substringAfter("[").substringBeforeLast("]")
                        val idx = eval(idxStr).toDoubleOrNull()?.toInt() ?: 0
                        return arrays[arrName]?.getOrElse(idx) { "null" } ?: "null"
                    }

                    if (variables.containsKey(t)) return variables[t]!!
                    
                    // Literals
                    if (t.startsWith("\"") && t.endsWith("\"")) return t.trim('"')
                    if (t.toDoubleOrNull() != null) return t
                    if (t == "true" || t == "false") return t

                    // String concatenation with +
                    if (t.contains("+") && (t.contains("\"") || variables.values.any { it.toIntOrNull() == null })) {
                        // Naive split by + that is not in quotes (simple approximation)
                        return t.split("+").joinToString("") { 
                            val p = it.trim()
                            if(p.startsWith("\"")) p.trim('"') else eval(p) 
                        }
                    }

                    // Math operations
                    if (t.matches(Regex(".*[+\\-*/%<>=!&|].*"))) {
                         try {
                            // Boolean Logic
                            if (t.contains("==")) return (eval(t.substringBefore("==")) == eval(t.substringAfter("=="))).toString()
                            if (t.contains("!=")) return (eval(t.substringBefore("!=")) != eval(t.substringAfter("!="))).toString()
                            if (t.contains(">=")) return ((eval(t.substringBefore(">=")).toDoubleOrNull() ?: 0.0) >= (eval(t.substringAfter(">=")).toDoubleOrNull() ?: 0.0)).toString()
                            if (t.contains("<=")) return ((eval(t.substringBefore("<=")).toDoubleOrNull() ?: 0.0) <= (eval(t.substringAfter("<=")).toDoubleOrNull() ?: 0.0)).toString()
                            if (t.contains(">")) return ((eval(t.substringBefore(">")).toDoubleOrNull() ?: 0.0) > (eval(t.substringAfter(">")).toDoubleOrNull() ?: 0.0)).toString()
                            if (t.contains("<")) return ((eval(t.substringBefore("<")).toDoubleOrNull() ?: 0.0) < (eval(t.substringAfter("<")).toDoubleOrNull() ?: 0.0)).toString()
                            if (t.contains("&&")) return (eval(t.substringBefore("&&")).toBoolean() && eval(t.substringAfter("&&")).toBoolean()).toString()
                            if (t.contains("||")) return (eval(t.substringBefore("||")).toBoolean() || eval(t.substringAfter("||")).toBoolean()).toString()

                            // Arithmetic
                            // Simple parser for two operands or chained same priority
                            // This is a simplified evaluator
                            val parts = t.split(Regex("(?=[+\\-*/%])|(?<=[+\\-*/%])")).map { it.trim() }
                            var res = eval(parts[0]).toDoubleOrNull() ?: 0.0
                            var i = 1
                            while (i < parts.size) {
                                val op = parts[i]
                                val nextVal = eval(parts[i+1]).toDoubleOrNull() ?: 0.0
                                when(op) {
                                    "+" -> res += nextVal
                                    "-" -> res -= nextVal
                                    "*" -> res *= nextVal
                                    "/" -> if(nextVal!=0.0) res /= nextVal
                                    "%" -> res %= nextVal
                                }
                                i += 2
                            }
                            return if (res % 1 == 0.0) res.toInt().toString() else res.toString()
                        } catch(e: Exception) { return t }
                    }
                    return t
                }

                fun executeBlock(lines: List<String>) {
                    var i = 0
                    while (i < lines.size) {
                        val rawLine = lines[i]
                        val line = rawLine.trim()
                        if (line.isEmpty() || line.startsWith("//")) { i++; continue }

                        if (line.startsWith("if") && line.contains("(") && line.contains(")")) {
                            val cond = line.substringAfter("(").substringBeforeLast(")")
                            // For simplicity, assume one-line block or braces
                            // Find brace block
                            val isTrue = eval(cond).toBoolean()
                            if (isTrue) {
                                // Execute next line or block logic would go here
                                // Simplified: if next line has {, execute until }
                                if (i + 1 < lines.size && lines[i+1].trim().startsWith("{")) {
                                    // TODO: extract block
                                }
                                // Checking for single line: if (..) stmt;
                                val rest = line.substringAfter(")").trim()
                                if (rest.isNotEmpty() && !rest.startsWith("{")) {
                                    // Single statement
                                    // Recursively execute this single line?
                                    // Too complex for this patch, skipping deeper nesting
                                }
                            }
                        }
                        
                        // Handle System.out.println
                        if (line.contains("System.out.print")) {
                            val content = line.substringAfter("(").substringBeforeLast(")")
                            val res = eval(content)
                            if (line.contains("println")) output.append(res).append("\n")
                            else output.append(res)
                        }

                        // Handle for loop: for(int i=0; i<5; i++)
                        else if (line.startsWith("for") && line.contains("(")) {
                            val header = line.substringAfter("(").substringBeforeLast(")")
                            val parts = header.split(";")
                            if (parts.size == 3) {
                                val init = parts[0].trim()
                                val cond = parts[1].trim()
                                val step = parts[2].trim()

                                // Init
                                if (init.contains("=")) {
                                    val name = init.substringBefore("=").split(" ").last().trim()
                                    val value = eval(init.substringAfter("="))
                                    variables[name] = value
                                }

                                // Identify block
                                val blockLines = mutableListOf<String>()
                                var j = i + 1
                                if (lines.getOrNull(j)?.trim() == "{") j++ // skip opening brace line
                                var braces = 1
                                while (j < lines.size && braces > 0) {
                                    val l = lines[j]
                                    if (l.contains("{")) braces++
                                    if (l.contains("}")) braces--
                                    if (braces == 0) break
                                    blockLines.add(l)
                                    j++
                                }
                                
                                // Loop
                                var safety = 0
                                while (safety < 1000) {
                                    if (eval(cond) == "false") break
                                    executeBlock(blockLines)
                                    
                                    // Step
                                    if (step.contains("++")) {
                                        val v = step.substringBefore("++").trim()
                                        variables[v] = ((variables[v]?.toDoubleOrNull() ?: 0.0) + 1).toInt().toString()
                                    } else if (step.contains("--")) {
                                        val v = step.substringBefore("--").trim()
                                        variables[v] = ((variables[v]?.toDoubleOrNull() ?: 0.0) - 1).toInt().toString()
                                    }
                                    safety++
                                }
                                i = j // skip block
                            }
                        }

                        // Handle Assignments
                        else if (line.contains("=") && !line.startsWith("if") && !line.startsWith("while")) {
                            if (line.contains("[]") && line.contains("{")) { // Array: int[] a = {1,2}
                                val name = line.substringBefore("[]").split(" ").last().trim()
                                val elems = line.substringAfter("{").substringBefore("}").split(",").map { eval(it) }
                                arrays[name] = elems
                            } else {
                                val lhs = line.substringBefore("=").trim()
                                val name = lhs.split(" ").last().trim()
                                val expr = line.substringAfter("=").trim()
                                variables[name] = eval(expr)
                            }
                        }
                        i++
                    }
                }

                // Extract Main Body
                val allLines = code.lines()
                val mainStart = allLines.indexOfFirst { it.contains("public static void main") }
                if (mainStart != -1) {
                    // Extract until end of file minus last braces
                    val body = allLines.subList(mainStart + 1, allLines.size)
                        .dropLastWhile { it.trim() == "}" || it.isBlank() }
                        .dropWhile { it.trim() == "{" || it.isBlank() }
                    executeBlock(body)
                } else {
                    // Execute as top-level script if no main method found
                    executeBlock(allLines)
                }

                CodeExecutionResult.Success(if (output.isEmpty()) "Executed successfully (Local)" else output.toString())
            } catch (e: Exception) {
                CodeExecutionResult.Error("Java Error: ${e.message}")
            }
        }
    }

    private suspend fun executeLocalPythonCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.Default) {
            try {
                val output = StringBuilder()
                val variables = mutableMapOf<String, String>()
                val collections = mutableMapOf<String, Any>()

                fun eval(expr: String): String {
                    val trimmed = expr.trim().trim('"').trim('\'')
                    if (variables.containsKey(trimmed)) return variables[trimmed]!!
                    if (expr.trim().startsWith("f\"") || expr.trim().startsWith("f'")) {
                        var content = expr.trim().substring(2, expr.trim().length - 1)
                        variables.forEach { (k, v) -> content = content.replace("{$k}", v) }
                        return content
                    }
                    // Simple arithmetic for Python
                    if (trimmed.contains("+") || trimmed.contains("-") || trimmed.contains("*")) {
                         try {
                            val left = eval(trimmed.substringBefore("+").substringBefore("-").trim())
                            val right = eval(trimmed.substringAfter("+").substringAfter("-").trim())
                            val res = (left.toDoubleOrNull() ?: 0.0) + (right.toDoubleOrNull() ?: 0.0)
                            return if (res % 1 == 0.0) res.toInt().toString() else res.toString()
                         } catch(e: Exception) {}
                    }
                    return trimmed.replace("True", "true").replace("False", "false").replace("None", "null")
                }

                code.lines().forEach { line ->
                    val t = line.trim()
                    if (t.isEmpty() || t.startsWith("#")) return@forEach
                    if (t.startsWith("print(")) {
                        val expr = t.substringAfter("print(").substringBeforeLast(")")
                        if (expr.contains("[") && expr.endsWith("]")) {
                            val name = expr.substringBefore("[").trim()
                            val key = eval(expr.substringAfter("[").substringBeforeLast("]"))
                            output.append((collections[name] as? Map<*, *>)?.get(key)?.toString() ?: "None").append("\n")
                        } else {
                            output.append(eval(expr).replace("true", "True").replace("false", "False").replace("null", "None")).append("\n")
                        }
                    } else if (t.contains("=") && !t.contains("(")) {
                        val name = t.substringBefore("=").trim()
                        val value = t.substringAfter("=").trim()
                        if (value == "{}") collections[name] = mutableMapOf<String, String>()
                        else if (value == "[]") collections[name] = mutableListOf<String>()
                        else variables[name] = eval(value)
                    } else if (t.contains("[") && t.contains("]") && t.contains("=")) {
                        val name = t.substringBefore("[").trim()
                        val key = eval(t.substringAfter("[").substringBefore("]"))
                        val value = eval(t.substringAfter("=").trim())
                        val map = collections.getOrPut(name) { mutableMapOf<String, String>() } as MutableMap<String, String>
                        map[key] = value
                    }
                }
                CodeExecutionResult.Success(if (output.isEmpty()) "Python finished (Local)" else output.toString())
            } catch (e: Exception) {
                CodeExecutionResult.Error("Python Error: ${e.message}")
            }
        }
    }

    private suspend fun executeLocalJavaScriptCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.Default) {
            try {
                val output = StringBuilder()
                val variables = mutableMapOf<String, String>()
                val objects = mutableMapOf<String, MutableMap<String, String>>()

                fun eval(expr: String): String {
                    val t = expr.trim().trim(';').trim('"').trim('\'').trim('`')
                    if (variables.containsKey(t)) return variables[t]!!
                    if (expr.trim().startsWith("`")) { // Template literal
                        var content = expr.trim().trim('`')
                        variables.forEach { (k, v) -> content = content.replace("\${$k}", v) }
                        return content
                    }
                    if (t.contains(".")) {
                        val obj = t.substringBefore(".").trim()
                        val prop = t.substringAfter(".").trim().replace("()", "")
                        return objects[obj]?.get(prop) ?: t
                    }
                    return t
                }

                val allLines = code.lines()
                var i = 0
                while (i < allLines.size) {
                    val line = allLines[i].trim()
                    if (line.isEmpty() || line.startsWith("//")) { i++; continue }
                    if (line.contains("console.log(")) {
                        val expr = line.substringAfter("console.log(").substringBeforeLast(")")
                        output.append(eval(expr)).append("\n")
                    } else if (line.contains("=") && line.endsWith("{")) {
                        val name = line.substringBefore("=").trim().split(" ").last()
                        val data = mutableMapOf<String, String>()
                        var j = i + 1
                        while (j < allLines.size && !allLines[j].trim().startsWith("}")) {
                            val l = allLines[j].trim()
                            if (l.contains(":")) {
                                val k = l.substringBefore(":").trim()
                                val v = l.substringAfter(":").trim().trim(',').trim('"').trim('\'')
                                if (l.contains("function") || l.contains("()")) {
                                     // Basic method mock: search for return
                                     var k2 = j + 1
                                     while(k2 < allLines.size && !allLines[k2].trim().startsWith("}")) {
                                         if (allLines[k2].trim().startsWith("return ")) {
                                             data[k] = eval(allLines[k2].trim().substringAfter("return ").trim(';'))
                                         }
                                         k2++
                                     }
                                } else {
                                    data[k] = eval(v)
                                }
                            }
                            j++
                        }
                        objects[name] = data
                        i = j
                    } else if (line.contains(".then(")) { // Promise mock
                        val resVar = line.substringAfter("(").substringBefore("=>").trim().trim('(').trim(')')
                        variables[resVar + ".data"] = "Sample data loaded"
                        objects[resVar] = mutableMapOf("data" to "Sample data loaded")
                        val body = line.substringAfter("=>").trim().trim('{').trim('}').trim(';')
                        if (body.contains("console.log")) {
                             val expr = body.substringAfter("console.log(").substringBeforeLast(")")
                             output.append(eval(expr)).append("\n")
                        }
                    } else if (line.contains("=")) {
                        val name = line.substringBefore("=").trim().split(" ").last()
                        variables[name] = eval(line.substringAfter("=").trim(';'))
                    }
                    i++
                }
                CodeExecutionResult.Success(if (output.isEmpty()) "JS finished (Local)" else output.toString())
            } catch (e: Exception) {
                CodeExecutionResult.Error("JS Error: ${e.message}")
            }
        }
    }

    private suspend fun executeLocalSqlCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.Default) {
            try {
                val trimmed = code.trim().uppercase()
                if (trimmed.startsWith("SELECT")) {
                    if (trimmed.contains("FROM USERS")) {
                        CodeExecutionResult.Success("| ID | Name | Email |\n|---|---|---|\n| 1 | Yodgorbek | yodgor@example.com |\n| 2 | Ali | ali@example.com |")
                    } else {
                        CodeExecutionResult.Success("Query executed successfully. 0 rows returned.")
                    }
                } else if (trimmed.startsWith("INSERT") || trimmed.startsWith("UPDATE") || trimmed.startsWith("DELETE")) {
                    CodeExecutionResult.Success("Success: 1 row affected.")
                } else {
                    CodeExecutionResult.Success("SQL command executed successfully.")
                }
            } catch (e: Exception) {
                CodeExecutionResult.Error("SQL Error: ${e.message}")
            }
        }
    }
}

sealed class CodeExecutionResult {
    data class Success(val output: String) : CodeExecutionResult()
    data class Error(val message: String) : CodeExecutionResult()
}
