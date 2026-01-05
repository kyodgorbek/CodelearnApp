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
                val arrayVars = mutableMapOf<String, List<String>>()
                
                fun resolveValue(content: String, currentVars: Map<String, String>): String {
                    var resolved = content.trim()
                    
                    // String interpolation: $name or ${name}
                    currentVars.forEach { (name, value) ->
                        resolved = resolved.replace("${'$'}$name", value)
                        resolved = resolved.replace("${'$'}{$name}", value)
                    }

                    // Concatenation: "Hello " + name
                    if (resolved.contains("+") && (resolved.contains("\"") || resolved.contains("'"))) {
                        return resolved.split("+").joinToString("") { part ->
                            val p = part.trim().trim('"').trim('\'')
                            currentVars[p] ?: p
                        }
                    }

                    return currentVars[resolved] ?: resolved
                }

                fun processBlock(lines: List<String>, localVars: Map<String, String>) {
                    lines.forEach { line ->
                        val trimmed = line.trim()
                        if (trimmed.isEmpty() || trimmed.startsWith("//")) return@forEach
                        
                        val currentVars = variables + localVars
                        
                        // Assignment or Reassignment
                        if (trimmed.contains("=") && !trimmed.contains("(")) {
                            val assignmentPart = if (trimmed.startsWith("val ") || trimmed.startsWith("var ")) {
                                trimmed.substringAfter("val ").substringAfter("var ")
                            } else {
                                trimmed
                            }
                            val name = assignmentPart.substringBefore("=").trim().split(":").first().trim()
                            val rawValue = assignmentPart.substringAfter("=").trim().trim(';')
                            val value = resolveValue(rawValue, currentVars).trim('"')
                            variables[name] = value
                        }
                        
                        if (trimmed.contains("println(")) {
                            val rawContent = trimmed.substringAfter("println(").substringBeforeLast(")")
                            val content = resolveValue(rawContent, currentVars)
                            output.append(content).append("\n")
                        }
                    }
                }

                val allLines = code.lines()
                var i = 0
                while (i < allLines.size) {
                    val line = allLines[i].trim()
                    if (line.isEmpty() || line.startsWith("//")) { i++; continue }
                    
                    if (line.contains("listOf(") || line.contains("arrayOf(")) {
                        val name = line.substringBefore("=").trim().split(" ").last()
                        val items = line.substringAfter("(").substringBeforeLast(")").split(",")
                            .map { it.trim().trim('"').trim('\'') }
                        arrayVars[name] = items
                    }
                    else if (line.startsWith("for") && line.contains(" in ")) {
                        val valName = line.substringAfter("(").substringBefore(" in ").trim()
                        val rangePart = line.substringAfter(" in ").substringBefore(")").trim()
                        
                        val blockEnd = allLines.indexOfFirst { it.trim() == "}" && allLines.indexOf(it) > i }
                        if (blockEnd != -1) {
                            val blockLines = allLines.subList(i + 1, blockEnd)
                            
                            if (rangePart.contains("..")) {
                                val start = rangePart.substringBefore("..").trim().toIntOrNull() ?: 0
                                val end = rangePart.substringAfter("..").trim().toIntOrNull() ?: 0
                                for (v in start..end) {
                                    processBlock(blockLines, mapOf(valName to v.toString()))
                                }
                            } else if (arrayVars.containsKey(rangePart)) {
                                arrayVars[rangePart]?.forEach { item ->
                                    processBlock(blockLines, mapOf(valName to item))
                                }
                            }
                            i = blockEnd
                        }
                    }
                    else {
                        processBlock(listOf(line), emptyMap())
                    }
                    i++
                }
                
                CodeExecutionResult.Success(if (output.isEmpty()) "Executed successfully" else output.toString())
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
                val listVars = mutableMapOf<String, List<String>>()

                fun resolveValue(content: String, currentVars: Map<String, String>): String {
                    var resolved = content.trim()
                    
                    // f-strings or simple {var}
                    val inFString = content.startsWith("f\"") || content.startsWith("f'")
                    val cleanContent = if (inFString) resolved.substring(1).trim('"').trim('\'') else resolved.trim('"').trim('\'')
                    
                    var finalValue = cleanContent
                    currentVars.forEach { (name, value) ->
                        finalValue = finalValue.replace("{$name}", value)
                    }
                    
                    if (!inFString && currentVars.containsKey(cleanContent) && !resolved.contains(" ")) {
                        return currentVars[cleanContent] ?: cleanContent
                    }
                    
                    return finalValue
                }

                fun processBlock(lines: List<String>, localVars: Map<String, String>) {
                    lines.forEach { line ->
                        val trimmed = line.trim()
                        if (trimmed.isEmpty() || trimmed.startsWith("#")) return@forEach
                        
                        val currentVars = variables + localVars
                        
                        if (trimmed.contains("=") && !trimmed.contains("(")) {
                            val name = trimmed.substringBefore("=").trim()
                            val rawValue = trimmed.substringAfter("=").trim()
                            val value = resolveValue(rawValue, currentVars)
                            variables[name] = value
                        }

                        if (trimmed.startsWith("print(")) {
                            val rawContent = trimmed.substringAfter("print(").substringBeforeLast(")")
                            val content = resolveValue(rawContent, currentVars)
                            output.append(content).append("\n")
                        }
                    }
                }

                val allLines = code.lines()
                var i = 0
                while (i < allLines.size) {
                    val line = allLines[i].trim()
                    if (line.isEmpty() || line.startsWith("#")) { i++; continue }
                    
                    if (line.contains("=") && line.contains("[") && line.contains("]")) {
                        val name = line.substringBefore("=").trim()
                        val items = line.substringAfter("[").substringBeforeLast("]").split(",")
                            .map { it.trim().trim('"').trim('\'') }
                        listVars[name] = items
                    }
                    else if (line.startsWith("for ") && line.contains(" in ") && line.endsWith(":")) {
                        val valName = line.substringAfter("for ").substringBefore(" in ").trim()
                        val rangePart = line.substringAfter(" in ").substringBefore(":").trim().trim(')')
                        
                        val blockLines = mutableListOf<String>()
                        var j = i + 1
                        while (j < allLines.size && (allLines[j].startsWith("    ") || allLines[j].startsWith("\t") || allLines[j].trim().isEmpty())) {
                            if (allLines[j].trim().isNotEmpty()) blockLines.add(allLines[j])
                            j++
                        }
                        
                        if (rangePart.startsWith("range(")) {
                            val r = rangePart.substringAfter("(").substringBefore(")").split(",").map { it.trim().toIntOrNull() ?: 0 }
                            val start = if (r.size == 1) 0 else r[0]
                            val end = if (r.size == 1) r[0] else r[1]
                            for (v in start until end) {
                                processBlock(blockLines, mapOf(valName to v.toString()))
                            }
                        } else if (listVars.containsKey(rangePart)) {
                            listVars[rangePart]?.forEach { item ->
                                processBlock(blockLines, mapOf(valName to item))
                            }
                        }
                        i = j - 1
                    }
                    else {
                        processBlock(listOf(line), emptyMap())
                    }
                    i++
                }
                CodeExecutionResult.Success(if (output.isEmpty()) "Script finished" else output.toString())
            } catch (e: Exception) {
                CodeExecutionResult.Error(e.message ?: "Python Execution failed")
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
                CodeExecutionResult.Error(e.message ?: "SQL Execution failed")
            }
        }
    }
    
    suspend fun executeJavaScriptCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
            try {
                val output = StringBuilder()
                val variables = mutableMapOf<String, String>()
                val arrayVars = mutableMapOf<String, List<String>>()
                val objectVars = mutableMapOf<String, Map<String, String>>()

                fun resolveValue(content: String, currentVars: Map<String, String>, context: Map<String, String>? = null): String {
                    var resolved = content.trim()
                    
                    // Template literals: `... ${name} ...`
                    if (resolved.startsWith("`") && resolved.endsWith("`")) {
                        var inner = resolved.substring(1, resolved.length - 1)
                        val allAvailable = variables + (context ?: emptyMap()) + currentVars
                        allAvailable.forEach { (name, value) ->
                            inner = inner.replace("${'$'}{$name}", value)
                            inner = inner.replace("${'$'}{this.$name}", value)
                        }
                        return inner
                    }

                    // Member access: book.title or book.getInfo()
                    if (resolved.contains(".")) {
                        val objName = resolved.substringBefore(".").trim()
                        val member = resolved.substringAfter(".").trim()
                        val obj = objectVars[objName]
                        if (obj != null) {
                            val cleanMember = member.replace("()", "")
                            return obj[cleanMember] ?: resolved
                        }
                    }

                    // Concatenation: "a" + b
                    if (resolved.contains("+") && (resolved.contains("\"") || resolved.contains("'") || resolved.contains("`"))) {
                        return resolved.split("+").joinToString("") { part ->
                            val p = part.trim().trim('"').trim('\'').trim('`')
                            val all = variables + (context ?: emptyMap()) + currentVars
                            all[p] ?: p
                        }
                    }

                    // Array access: fruits[0]
                    if (resolved.contains("[") && resolved.endsWith("]")) {
                        val arrName = resolved.substringBefore("[").trim()
                        val indexStr = resolved.substringAfter("[").substringBeforeLast("]").trim()
                        val index = (currentVars[indexStr] ?: indexStr).toIntOrNull() ?: 0
                        val list = arrayVars[arrName]
                        if (list != null && index >= 0 && index < list.size) {
                            return list[index]
                        }
                    }

                    return (variables + (context ?: emptyMap()) + currentVars)[resolved] ?: resolved.trim('"').trim('\'')
                }

                fun processBlock(lines: List<String>, localVars: Map<String, String>, context: Map<String, String>? = null) {
                    lines.forEach { line ->
                        val trimmed = line.trim()
                        if (trimmed.isEmpty() || trimmed.startsWith("//")) return@forEach
                        
                        val currentVars = localVars
                        
                        if (trimmed.contains("console.log(")) {
                            val argsRaw = trimmed.substringAfter("console.log(").substringBeforeLast(")")
                            val args = argsRaw.split(",").map { it.trim() }
                            val resolvedArgs = args.map { resolveValue(it, currentVars, context) }
                            output.append(resolvedArgs.joinToString(" ")).append("\n")
                        } else if (trimmed.contains(".then(")) {
                            // Basic Promise/Async mock
                            val resultVar = trimmed.substringAfter("(").substringBefore("=>").trim()
                                .trim('(').trim(')').trim()
                            val callbackBody = trimmed.substringAfter("=>").trim().trim('{').trim('}').trim(';')
                            // Mock an object result
                            objectVars[resultVar] = mapOf("data" to "Sample data loaded")
                            processBlock(listOf(callbackBody), emptyMap()) 
                        } else if (trimmed.contains("++") || trimmed.contains("--")) {
                            val isInc = trimmed.contains("++")
                            val name = if (isInc) trimmed.substringBefore("++").trim() else trimmed.substringBefore("--").trim()
                            val current = (variables[name] ?: localVars[name])?.toIntOrNull() ?: 0
                            variables[name] = (if (isInc) current + 1 else current - 1).toString()
                        } else if (trimmed.contains("=") && !trimmed.contains("(")) {
                            val assignmentPart = if (trimmed.startsWith("let ") || trimmed.startsWith("const ") || trimmed.startsWith("var ")) {
                                trimmed.substringAfter(" ").trim()
                            } else {
                                trimmed
                            }
                            val name = assignmentPart.substringBefore("=").trim()
                            val rawValue = assignmentPart.substringAfter("=").trim().trim(';')
                            val value = resolveValue(rawValue, currentVars, context)
                            variables[name] = value
                        }
                    }
                }

                val allLines = code.lines()
                var i = 0
                while (i < allLines.size) {
                    val line = allLines[i].trim()
                    if (line.isEmpty() || line.startsWith("//")) { i++; continue }
                    
                    // Object Definition: const book = { ... }
                    if (line.contains("=") && line.endsWith("{")) {
                        val objName = line.substringBefore("=").trim().split(" ").last()
                        val objData = mutableMapOf<String, String>()
                        var j = i + 1
                        while (j < allLines.size && !allLines[j].trim().startsWith("}")) {
                            val l = allLines[j].trim()
                            if (l.contains(":") && !l.contains("function") && !l.contains("()")) {
                                val key = l.substringBefore(":").trim()
                                val valRaw = l.substringAfter(":").trim().trim(',').trim(';').trim('"').trim('\'')
                                objData[key] = valRaw
                            } else if (l.contains("() {") || l.contains(": function")) {
                                // Simple method mock: find return value
                                val methodName = if (l.contains("() {")) l.substringBefore("(").trim() else l.substringBefore(":").trim()
                                var k = j + 1
                                while (k < allLines.size && !allLines[k].trim().startsWith("}")) {
                                    val methodLine = allLines[k].trim()
                                    if (methodLine.startsWith("return ")) {
                                        val retVal = methodLine.substringAfter("return ").trim().trim(';')
                                        objData[methodName] = resolveValue(retVal, emptyMap(), objData)
                                    }
                                    k++
                                }
                            }
                            j++
                        }
                        objectVars[objName] = objData
                        i = j
                    }
                    else if (line.contains("=") && line.contains("[") && line.contains("]")) {
                        val name = line.substringBefore("=").trim().split(" ").last()
                        val items = line.substringAfter("[").substringBeforeLast("]").split(",")
                            .map { it.trim().trim('"').trim('\'') }
                        arrayVars[name] = items
                    }
                    else if (line.startsWith("for") && (line.contains(" of ") || line.contains(";"))) {
                        if (line.contains(" of ")) {
                            val valName = line.substringAfter("(").substringBefore(" of ").trim().split(" ").last()
                            val arrName = line.substringAfter(" of ").substringBefore(")").trim()
                            val blockEnd = allLines.indexOfFirst { it.trim().startsWith("}") && allLines.indexOf(it) > i }
                            if (blockEnd != -1) {
                                val blockLines = allLines.subList(i + 1, blockEnd)
                                arrayVars[arrName]?.forEach { item ->
                                    processBlock(blockLines, mapOf(valName to item))
                                }
                                i = blockEnd
                            }
                        } else {
                            val header = line.substringAfter("(").substringBefore(")")
                            val parts = header.split(";")
                            if (parts.size >= 2) {
                                val valName = parts[0].substringBefore("=").trim().split(" ").last()
                                val startVal = parts[0].substringAfter("=").trim().toIntOrNull() ?: 0
                                val endCondition = parts[1].trim()
                                val endVal = endCondition.replace("[^0-9]".toRegex(), "").toIntOrNull() ?: 0
                                val isInclusive = endCondition.contains("<=") || endCondition.contains(">=")

                                val blockEnd = allLines.indexOfFirst { it.trim().startsWith("}") && allLines.indexOf(it) > i }
                                if (blockEnd != -1) {
                                    val blockLines = allLines.subList(i + 1, blockEnd)
                                    val range = if (isInclusive) startVal..endVal else startVal until endVal
                                    for (v in range) {
                                        processBlock(blockLines, mapOf(valName to v.toString()))
                                    }
                                    i = blockEnd
                                }
                            }
                        }
                    }
                    else {
                        processBlock(listOf(line), emptyMap())
                    }
                    i++
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
                val arrayVars = mutableMapOf<String, List<String>>()

                fun processBlock(lines: List<String>, localVars: Map<String, String>) {
                    lines.forEach { line ->
                        val trimmed = line.trim()
                        if (trimmed.isEmpty() || trimmed.startsWith("//")) return@forEach
                        
                        val currentVars = variables + localVars
                        
                        if (trimmed.contains("=") && !trimmed.contains("(")) {
                            val assignmentPart = if (trimmed.startsWith("String ") || trimmed.startsWith("int ") || trimmed.startsWith("double ") || trimmed.startsWith("boolean ")) {
                                trimmed.substringAfter(" ").trim()
                            } else {
                                trimmed
                            }
                            val name = assignmentPart.substringBefore("=").trim().split(" ").last()
                            val rawValue = assignmentPart.substringAfter("=").trim().trim(';')
                            val value = rawValue.trim('"').trim('\'')
                            variables[name] = currentVars[value] ?: value
                        }

                        if (trimmed.contains("System.out.println(")) {
                            val rawContent = trimmed.substringAfter("System.out.println(").substringBeforeLast(")")
                            val content = rawContent.trim().trim('"').trim('\'')
                            
                            if (content.contains("+")) {
                                val resolvedPats = content.split("+").map { part ->
                                    val p = part.trim().trim('"').trim('\'')
                                    currentVars[p] ?: p
                                }
                                output.append(resolvedPats.joinToString("")).append("\n")
                            } else {
                                output.append(currentVars[content] ?: content).append("\n")
                            }
                        }
                    }
                }

                val allLines = code.lines()
                var i = 0
                while (i < allLines.size) {
                    val line = allLines[i].trim()
                    if (line.isEmpty() || line.startsWith("//")) { i++; continue }
                    
                    if (line.contains("{") && line.contains("}") && line.contains("[]") && line.contains("=")) {
                         val name = line.substringBefore("=").trim().split(" ").last()
                         val items = line.substringAfter("{").substringBefore("}").split(",")
                             .map { it.trim().trim('"').trim('\'') }
                         arrayVars[name] = items
                    }
                    else if (line.startsWith("for") && line.contains(";")) {
                        val header = line.substringAfter("(").substringBefore(")")
                        val parts = header.split(";")
                        if (parts.size >= 2) {
                            val valName = parts[0].substringBefore("=").trim().split(" ").last()
                            val startVal = parts[0].substringAfter("=").trim().toIntOrNull() ?: 0
                            val endCondition = parts[1].trim()
                            val endVal = endCondition.replace("[^0-9]".toRegex(), "").toIntOrNull() ?: 0
                            val isInclusive = endCondition.contains("<=") || endCondition.contains(">=")

                            val blockEnd = allLines.indexOfFirst { it.trim() == "}" && allLines.indexOf(it) > i }
                            if (blockEnd != -1) {
                                val blockLines = allLines.subList(i + 1, blockEnd)
                                val range = if (isInclusive) startVal..endVal else startVal until endVal
                                for (v in range) {
                                    processBlock(blockLines, mapOf(valName to v.toString()))
                                }
                                i = blockEnd
                            }
                        }
                    }
                    else if (line.startsWith("for") && line.contains(":")) {
                        val valName = line.substringAfter("(").substringBefore(":").trim().split(" ").last()
                        val arrName = line.substringAfter(":").substringBefore(")").trim()
                        
                        val blockEnd = allLines.indexOfFirst { it.trim() == "}" && allLines.indexOf(it) > i }
                        if (blockEnd != -1) {
                            val blockLines = allLines.subList(i + 1, blockEnd)
                            arrayVars[arrName]?.forEach { item ->
                                processBlock(blockLines, mapOf(valName to item))
                            }
                            i = blockEnd
                        }
                    }
                    else {
                        processBlock(listOf(line), emptyMap())
                    }
                    i++
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
