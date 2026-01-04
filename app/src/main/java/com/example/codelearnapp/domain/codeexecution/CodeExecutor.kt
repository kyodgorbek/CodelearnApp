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
                
                fun processBlock(lines: List<String>, localVars: Map<String, String>) {
                    lines.forEach { line ->
                        val trimmed = line.trim()
                        val currentVars = variables + localVars
                        
                        // Assignment
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
                            
                            currentVars.forEach { (name, value) ->
                                content = content.replace("${'$'}$name", value)
                                content = content.replace("${'$'}{$name}", value)
                            }
                            
                            if (currentVars.containsKey(content)) {
                                output.append(currentVars[content]).append("\n")
                            } else {
                                output.append(content).append("\n")
                            }
                        }
                    }
                }

                val allLines = code.lines()
                var i = 0
                while (i < allLines.size) {
                    val line = allLines[i].trim()
                    
                    // Array/List
                    if (line.contains("listOf(") || line.contains("arrayOf(")) {
                        val name = line.substringBefore("=").trim().split(" ").last()
                        val items = line.substringAfter("(").substringBeforeLast(")").split(",")
                            .map { it.trim().trim('"').trim('\'') }
                        arrayVars[name] = items
                    }
                    // Loop: for (i in 0..4)
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

                fun processBlock(lines: List<String>, localVars: Map<String, String>) {
                    lines.forEach { line ->
                        val trimmed = line.trim()
                        val currentVars = variables + localVars
                        
                        if (trimmed.contains("=") && !trimmed.startsWith("print") && !trimmed.startsWith("if")) {
                            val name = trimmed.substringBefore("=").trim()
                            val value = trimmed.substringAfter("=").trim().trim('"').trim('\'')
                            variables[name] = value
                        }

                        if (trimmed.startsWith("print(")) {
                            var content = trimmed.substringAfter("print(").substringBeforeLast(")")
                                .trim().trim('"').trim('\'')
                            if (content.startsWith("f")) content = content.substring(1).trim('"').trim('\'')

                            currentVars.forEach { (name, value) ->
                                content = content.replace("{$name}", value)
                            }
                            
                            if (currentVars.containsKey(content)) {
                                output.append(currentVars[content]).append("\n")
                            } else {
                                output.append(content).append("\n")
                            }
                        }
                    }
                }

                val allLines = code.lines()
                var i = 0
                while (i < allLines.size) {
                    val line = allLines[i].trim()
                    
                    if (line.contains("=") && line.contains("[") && line.contains("]")) {
                        val name = line.substringBefore("=").trim()
                        val items = line.substringAfter("[").substringBefore("]").split(",")
                            .map { it.trim().trim('"').trim('\'') }
                        listVars[name] = items
                    }
                    else if (line.startsWith("for ") && line.endsWith(":")) {
                        val valName = line.substringAfter("for ").substringBefore(" in ").trim()
                        val rangePart = line.substringAfter(" in ").substringBefore(":").trim().trim(')')
                        
                        // Block is all indented lines following
                        val blockLines = mutableListOf<String>()
                        var j = i + 1
                        while (j < allLines.size && (allLines[j].startsWith("    ") || allLines[j].startsWith("\t"))) {
                            blockLines.add(allLines[j])
                            j++
                        }
                        
                        if (rangePart.startsWith("range(")) {
                            val r = rangePart.substringAfter("(").substringBefore(")").split(",").map { it.trim().toIntOrNull() ?: 0 }
                            val rangeRange = if (r.size == 1) 0 until r[0] else r[0] until r[1]
                            for (v in rangeRange) {
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
    
    suspend fun executeJavaScriptCode(code: String): CodeExecutionResult {
        return withContext(Dispatchers.IO) {
            try {
                val output = StringBuilder()
                val variables = mutableMapOf<String, String>()
                val arrayVars = mutableMapOf<String, List<String>>()

                fun processBlock(lines: List<String>, localVars: Map<String, String>) {
                    lines.forEach { line ->
                        val trimmed = line.trim()
                        val currentVars = variables + localVars
                        
                        if (trimmed.contains("=") && (trimmed.startsWith("let ") || trimmed.startsWith("const ") || trimmed.startsWith("var "))) {
                            val name = trimmed.substringAfter(" ").substringBefore("=").trim()
                            val value = trimmed.substringAfter("=").trim().trim(';').trim('"').trim('\'').trim('`')
                            variables[name] = value
                        }

                        if (trimmed.contains("console.log(")) {
                            val argsRaw = trimmed.substringAfter("console.log(").substringBeforeLast(")")
                            val args = argsRaw.split(",").map { it.trim() }
                            
                            val resolvedArgs = args.map { arg ->
                                var content = arg.trim('"').trim('\'').trim('`')
                                currentVars.forEach { (name, value) ->
                                    content = content.replace("${'$'}{$name}", value)
                                }
                                
                                if (content.contains("+")) {
                                    content.split("+").joinToString("") { part ->
                                        val p = part.trim().trim('"').trim('\'').trim('`')
                                        currentVars[p] ?: p
                                    }
                                } else {
                                    currentVars[content] ?: content
                                }
                            }
                            output.append(resolvedArgs.joinToString(" ")).append("\n")
                        }
                    }
                }

                val allLines = code.lines()
                var i = 0
                while (i < allLines.size) {
                    val line = allLines[i].trim()
                    
                    if (line.contains("=") && line.contains("[") && line.contains("]")) {
                        val name = line.substringBefore("=").trim().split(" ").last()
                        val items = line.substringAfter("[").substringBeforeLast("]").split(",")
                            .map { it.trim().trim('"').trim('\'') }
                        arrayVars[name] = items
                    }
                    else if (line.contains("=") && (line.startsWith("let ") || line.startsWith("const ") || line.startsWith("var "))) {
                        val name = line.substringAfter(" ").substringBefore(" =").trim()
                        val value = line.substringAfter("=").trim().trim(';').trim('"').trim('\'').trim('`')
                        variables[name] = value
                    }
                    else if (line.startsWith("for") && line.contains(" of ")) {
                        val valName = line.substringAfter("(").substringBefore(" of ").trim().split(" ").last()
                        val arrName = line.substringAfter(" of ").substringBefore(")").trim()
                        val blockEnd = allLines.indexOfFirst { it.trim() == "}" && allLines.indexOf(it) > i }
                        if (blockEnd != -1) {
                            val blockLines = allLines.subList(i + 1, blockEnd)
                            arrayVars[arrName]?.forEach { item ->
                                processBlock(blockLines, mapOf(valName to item))
                            }
                            i = blockEnd
                        }
                    }
                    else if (line.startsWith("for") && line.contains(";")) {
                        val loopHeader = line.substringAfter("(").substringBefore(")")
                        val parts = loopHeader.split(";")
                        if (parts.size >= 2) {
                            val valName = parts[0].substringBefore("=").trim().split(" ").last()
                            val start = parts[0].substringAfter("=").trim().toIntOrNull() ?: 0
                            val end = parts[1].trim().split(" ").last().toIntOrNull() ?: 0

                            val blockEnd = allLines.indexOfFirst { it.trim() == "}" && allLines.indexOf(it) > i }
                            if (blockEnd != -1) {
                                val blockLines = allLines.subList(i + 1, blockEnd)
                                for (valIdx in start until end) {
                                    processBlock(blockLines, mapOf(valName to valIdx.toString()))
                                }
                                i = blockEnd
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
                        val currentVars = variables + localVars
                        
                        if (trimmed.contains("=") && (trimmed.startsWith("int ") || trimmed.startsWith("double ") || trimmed.startsWith("String ") || trimmed.startsWith("boolean "))) {
                            val namePart = trimmed.substringBefore("=").trim().split(" ").last()
                            val valuePart = trimmed.substringAfter("=").trim().trim(';').trim('"').trim('\'')
                            variables[namePart] = valuePart
                        }

                        if (trimmed.contains("System.out.println(")) {
                            var content = trimmed.substringAfter("System.out.println(").substringBeforeLast(")")
                                .trim().trim('"').trim('\'')
                            
                            if (content.contains("+")) {
                                val resolvedPats = content.split("+").map { part ->
                                    val p = part.trim().trim('"').trim('\'')
                                    currentVars[p] ?: p
                                }
                                output.append(resolvedPats.joinToString("")).append("\n")
                            } else if (currentVars.containsKey(content)) {
                                output.append(currentVars[content]).append("\n")
                            } else {
                                output.append(content).append("\n")
                            }
                        }
                    }
                }

                val allLines = code.lines()
                var i = 0
                while (i < allLines.size) {
                    val line = allLines[i].trim()
                    
                    if (line.contains("{") && line.contains("}")) {
                        if (line.contains("[]") && line.contains("=")) {
                            val name = line.substringBefore("=").trim().split(" ").last()
                            val items = line.substringAfter("{").substringBefore("}").split(",")
                                .map { it.trim().trim('"').trim('\'') }
                            arrayVars[name] = items
                        }
                    }
                    else if (line.startsWith("for") && line.contains(";")) {
                        // for (int i = 0; i < 5; i++)
                        val header = line.substringAfter("(").substringBefore(")")
                        val parts = header.split(";")
                        if (parts.size >= 2) {
                            val valName = parts[0].substringBefore("=").trim().split(" ").last()
                            val start = parts[0].substringAfter("=").trim().toIntOrNull() ?: 0
                            val end = parts[1].trim().split(" ").last().toIntOrNull() ?: 0
                            
                            val blockEnd = allLines.indexOfFirst { it.trim() == "}" && allLines.indexOf(it) > i }
                            if (blockEnd != -1) {
                                val blockLines = allLines.subList(i + 1, blockEnd)
                                for (v in start until end) {
                                    processBlock(blockLines, mapOf(valName to v.toString()))
                                }
                                i = blockEnd
                            }
                        }
                    }
                    else if (line.startsWith("for") && line.contains(":")) {
                        // for (String s : list)
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
