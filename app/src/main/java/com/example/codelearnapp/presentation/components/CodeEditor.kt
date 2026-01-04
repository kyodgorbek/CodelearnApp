package com.example.codelearnapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CodeEditor(
    code: String,
    onCodeChange: (String) -> Unit,
    language: String,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    
    val visualTransformation = remember(language) {
        CodeHighlightTransformation(language)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF1E1E1E))
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Row {
            // Line numbers
            val lineCount = code.lines().size
            Column(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .width(24.dp),
                horizontalAlignment = androidx.compose.ui.Alignment.End
            ) {
                repeat(lineCount) { index ->
                    Text(
                        text = (index + 1).toString(),
                        style = TextStyle(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    )
                }
            }

            // Code input
            BasicTextField(
                value = code,
                onValueChange = onCodeChange,
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    color = Color.White,
                    lineHeight = 20.sp
                ),
                cursorBrush = SolidColor(Color.White),
                visualTransformation = visualTransformation
            )
        }
    }
}

class CodeHighlightTransformation(val language: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            highlightCode(text.text, language),
            OffsetMapping.Identity
        )
    }
}

fun highlightCode(code: String, language: String): AnnotatedString {
    val keywords = when (language.lowercase()) {
        "kotlin" -> listOf("fun", "val", "var", "if", "else", "for", "while", "class", "object", "return", "import", "package", "true", "false", "null")
        "python" -> listOf("def", "if", "else", "elif", "for", "while", "in", "import", "from", "as", "class", "return", "True", "False", "None", "print")
        "javascript" -> listOf("function", "let", "const", "var", "if", "else", "for", "while", "class", "return", "import", "export", "true", "false", "null", "console")
        else -> emptyList()
    }

    return buildAnnotatedString {
        val tokens = code.split(Regex("(?<=[^a-zA-Z0-9_])|(?=[^a-zA-Z0-9_])"))
        
        var inString = false
        var stringChar = ' '
        
        tokens.forEach { token ->
            when {
                token == "\"" || token == "'" -> {
                    if (!inString) {
                        inString = true
                        stringChar = token[0]
                        withStyle(style = SpanStyle(color = Color(0xFFCE9178))) { append(token) }
                    } else if (token[0] == stringChar) {
                        inString = false
                        withStyle(style = SpanStyle(color = Color(0xFFCE9178))) { append(token) }
                    } else {
                        withStyle(style = SpanStyle(color = Color(0xFFCE9178))) { append(token) }
                    }
                }
                inString -> {
                    withStyle(style = SpanStyle(color = Color(0xFFCE9178))) { append(token) }
                }
                keywords.contains(token) -> {
                    withStyle(style = SpanStyle(color = Color(0xFF569CD6))) { append(token) }
                }
                token.all { it.isDigit() } -> {
                    withStyle(style = SpanStyle(color = Color(0xFFB5CEA8))) { append(token) }
                }
                token.startsWith("//") || token.startsWith("#") -> {
                    withStyle(style = SpanStyle(color = Color(0xFF6A9955))) { append(token) }
                }
                else -> {
                    append(token)
                }
            }
        }
    }
}
