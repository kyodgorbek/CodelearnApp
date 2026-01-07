package com.example.codelearnapp.domain.preview.parser

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.codelearnapp.domain.preview.model.*

object ComposeCodeParser {

    fun parse(code: String): QirNode {
        // Wrap everything in a default root Column to ensure we return a single node
        val rootChildren = parseBlock(code)
        if (rootChildren.size == 1) return rootChildren.first()
        return QirColumn(QirModifier(padding = 16.dp, width = Dimension.Fill, height = Dimension.Fill), rootChildren)
    }

    private fun parseBlock(code: String): List<QirNode> {
        val nodes = mutableListOf<QirNode>()
        var remaining = code.trim()

        while (remaining.isNotEmpty()) {
            val nextComponent = findNextComponent(remaining)
            if (nextComponent == null) {
                // No more components found
                break
            }

            val (type, args, body, rest) = nextComponent
            val modifier = parseModifiers(args)
            
            val node: QirNode? = when (type) {
                "Column" -> QirColumn(modifier, parseBlock(body))
                "Row" -> QirRow(modifier, parseBlock(body))
                "Box" -> QirBox(modifier, parseBlock(body))
                "Button" -> {
                    // Button usually has a lambda content. 
                    // We treat the body as the content.
                    val contentNodes = parseBlock(body)
                    // If content is empty but text was passed in args? (Not standard Compose but possible in bad code)
                    // Standard Button: Button(onClick={}) { Text("...") }
                    // We take the first child as content, or wrap in Row if multiple
                    val content = if (contentNodes.isEmpty()) null else if (contentNodes.size == 1) contentNodes.first() else QirRow(QirModifier(), contentNodes)
                    QirButton(modifier, content)
                }
                "Text" -> {
                    // Extract string from args: Text("Hello")
                    val text = extractStringArg(args) ?: "Text"
                    QirText(modifier, text)
                }
                "Spacer" -> QirSpacer(modifier)
                else -> null // Skip unknown or handle generic
            }

            if (node != null) {
                nodes.add(node)
            }
            remaining = rest.trim()
        }
        return nodes
    }

    // Returns: Type, Args, BodyCode, RemainingCode
    private fun findNextComponent(code: String): ParseResult? {
        // Regex to find: Name followed by optional (...) followed by optional { ... }
        // We look for known component names
        val regex = """^(Column|Row|Box|Button|Text|Spacer)\s*""".toRegex()
        val match = regex.find(code) ?: return null
        
        val type = match.groupValues[1]
        var cursor = match.range.last + 1
        
        // 1. Parse Arguments (...)
        var args = ""
        if (cursor < code.length && code[cursor] == '(') {
            val argStart = cursor
            cursor = findMatchingBracket(code, cursor, '(', ')')
            args = code.substring(argStart + 1, cursor)
            cursor++ // Skip ')'
        }

        // 2. Parse Body { ... }
        var body = ""
        // Skip whitespace
        while (cursor < code.length && code[cursor].isWhitespace()) cursor++
        
        if (cursor < code.length && code[cursor] == '{') {
            val bodyStart = cursor
            cursor = findMatchingBracket(code, cursor, '{', '}')
            body = code.substring(bodyStart + 1, cursor)
            cursor++ // Skip '}'
        }

        return ParseResult(type, args, body, code.substring(cursor))
    }

    private fun findMatchingBracket(text: String, startIndex: Int, openChar: Char, closeChar: Char): Int {
        var count = 0
        for (i in startIndex until text.length) {
            if (text[i] == openChar) count++
            if (text[i] == closeChar) count--
            if (count == 0) return i
        }
        return text.length - 1 // Should shouldn't happen in valid code
    }

    private fun extractStringArg(args: String): String? {
        val start = args.indexOf('"')
        if (start == -1) return null
        val end = args.indexOf('"', start + 1)
        if (end == -1) return null
        return args.substring(start + 1, end)
    }

    private fun parseModifiers(args: String): QirModifier {
        var padding = 0.dp
        var width: Dimension = Dimension.Wrap
        var height: Dimension = Dimension.Wrap
        var color: Color? = null
        
        // Naive modifier parsing
        if (args.contains("fillMaxSize")) {
            width = Dimension.Fill
            height = Dimension.Fill
        }
        if (args.contains("fillMaxWidth")) width = Dimension.Fill
        if (args.contains("fillMaxHeight")) height = Dimension.Fill
        
        if (args.contains("padding")) {
            // Simplified: extract first number
            val num = """padding\s*\(\s*(\d+)""".toRegex().find(args)?.groupValues?.get(1)?.toIntOrNull()
            if (num != null) padding = num.dp
        }
        
        if (args.contains("background")) {
             if (args.contains("Color.Red")) color = Color.Red
             if (args.contains("Color.Blue")) color = Color.Blue
             if (args.contains("Color.Green")) color = Color.Green
             if (args.contains("Color.Gray")) color = Color.Gray
             if (args.contains("Color.Yellow")) color = Color.Yellow
             // Default fallback for demo
        }

        return QirModifier(padding, width, height, color)
    }

    data class ParseResult(val type: String, val args: String, val body: String, val rest: String)
}
