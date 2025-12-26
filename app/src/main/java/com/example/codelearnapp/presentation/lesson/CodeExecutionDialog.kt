package com.example.codelearnapp.presentation.lesson

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.codelearnapp.domain.codeexecution.CodeExecutionResult
import com.example.codelearnapp.domain.codeexecution.CodeExecutor

@Composable
fun CodeExecutionDialog(
    initialCode: String,
    language: String,
    onDismiss: () -> Unit
) {
    var code by remember { mutableStateOf(initialCode) }
    var output by remember { mutableStateOf("") }
    var isExecuting by remember { mutableStateOf(false) }
    val codeExecutor = remember { CodeExecutor() }
    
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    "Code Editor",
                    style = MaterialTheme.typography.titleLarge
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Code Input
                OutlinedTextField(
                    value = code,
                    onValueChange = { code = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily.Monospace
                    ),
                    placeholder = { Text("Write your code here...") }
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Run Button
                Button(
                    onClick = {
                        isExecuting = true
                        output = ""
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isExecuting
                ) {
                    Text(if (isExecuting) "Executing..." else "Run Code")
                }
                
                if (isExecuting) {
                    LaunchedEffect(code) {
                        val result = when (language.lowercase()) {
                            "kotlin" -> codeExecutor.executeKotlinCode(code)
                            "javascript" -> codeExecutor.executeJavaScriptCode(code)
                            "python" -> codeExecutor.executePythonCode(code)
                            else -> CodeExecutionResult.Error("Unsupported language")
                        }
                        
                        output = when (result) {
                            is CodeExecutionResult.Success -> result.output
                            is CodeExecutionResult.Error -> "Error: ${result.message}"
                        }
                        isExecuting = false
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Output
                if (output.isNotEmpty()) {
                    Text("Output:", style = MaterialTheme.typography.titleSmall)
                    Spacer(modifier = Modifier.height(4.dp))
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        color = Color(0xFF1E1E1E),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(
                            text = output,
                            modifier = Modifier
                                .padding(12.dp)
                                .verticalScroll(rememberScrollState()),
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = FontFamily.Monospace,
                            color = Color.White
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Close")
                }
            }
        }
    }
}