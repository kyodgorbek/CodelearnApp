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
                CodeExecutionResult.Success(if (output.isEmpty()) "Executed successfully" else output.toString())
            } catch (e: Exception) {
                CodeExecutionResult.Error("Kotlin Error: ${e.message}")
            }
        }
    }

    suspend fun executeJavaCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
            try {
                val output = StringBuilder()
                val variables = mutableMapOf<String, String>()
                val collections = mutableMapOf<String, Any>()

                fun eval(expr: String, currentVars: Map<String, String>): String {
                    var trimmed = expr.trim().trim(';').trim('"').trim('\'')
                    if (currentVars.containsKey(trimmed)) return currentVars[trimmed]!!
                    
                    if (trimmed.contains("+") && (expr.contains("\"") || expr.contains("'"))) {
                        return trimmed.split("+").joinToString("") { eval(it, currentVars) }
                    }

                    if (trimmed.matches(Regex(".*[+\\-*/%].*"))) {
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
                    if (trimmed.isEmpty() || trimmed.startsWith("//") || trimmed.startsWith("public class") || trimmed.startsWith("public static void main") || trimmed == "}") return@forEach
                    
                    if (trimmed.contains("System.out.println(")) {
                        val expr = trimmed.substringAfter("System.out.println(").substringBeforeLast(")")
                        if (expr.contains(".get(")) {
                            val name = expr.substringBefore(".get(").trim()
                            val key = eval(expr.substringAfter(".get(").substringBeforeLast(")"), variables)
                            output.append((collections[name] as? Map<*, *>)?.get(key)?.toString() ?: "null").append("\n")
                        } else {
                            output.append(eval(expr, variables)).append("\n")
                        }
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
                    } else if (trimmed.contains("=") && !trimmed.contains("(")) {
                        val types = listOf("String ", "int ", "double ", "boolean ", "HashMap<", "ArrayList<")
                        var clean = trimmed
                        types.forEach { if (clean.startsWith(it)) clean = clean.substringAfter(" ").trim() }
                        val name = clean.substringBefore("=").trim()
                        variables[name] = eval(clean.substringAfter("=").trim(';'), variables)
                    }
                }
                CodeExecutionResult.Success(if (output.isEmpty()) "Java finished" else output.toString())
            } catch (e: Exception) {
                CodeExecutionResult.Error("Java Error: ${e.message}")
            }
        }
    }

    suspend fun executePythonCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
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
                CodeExecutionResult.Success(if (output.isEmpty()) "Python finished" else output.toString())
            } catch (e: Exception) {
                CodeExecutionResult.Error("Python Error: ${e.message}")
            }
        }
    }

    suspend fun executeJavaScriptCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
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
                CodeExecutionResult.Success(if (output.isEmpty()) "JS finished" else output.toString())
            } catch (e: Exception) {
                CodeExecutionResult.Error("JS Error: ${e.message}")
            }
        }
    }

    suspend fun executeSqlCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
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
