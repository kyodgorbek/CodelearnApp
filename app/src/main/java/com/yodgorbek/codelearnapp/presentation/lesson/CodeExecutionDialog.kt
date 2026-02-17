package com.yodgorbek.codelearnapp.presentation.lesson

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.yodgorbek.codelearnapp.domain.codeexecution.CodeExecutionResult
import com.yodgorbek.codelearnapp.domain.codeexecution.CodeExecutor
import kotlinx.coroutines.launch

@Composable
fun CodeExecutionDialog(
    initialCode: String,
    language: String,
    onDismiss: () -> Unit
) {
    var code by remember { mutableStateOf(initialCode) }
    var stdin by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }
    var isExecuting by remember { mutableStateOf(false) }
    val codeExecutor = remember { CodeExecutor() }
    val scope = rememberCoroutineScope()

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.95f)
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

                Spacer(modifier = Modifier.height(8.dp))

                // Code Input
                Text("Source Code:", style = MaterialTheme.typography.labelMedium)
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

                // Input (Stdin)
                Text("Input (Stdin):", style = MaterialTheme.typography.labelMedium)
                OutlinedTextField(
                    value = stdin,
                    onValueChange = { stdin = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily.Monospace
                    ),
                    placeholder = { Text("Enter input for your program...") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Run Button
                Button(
                    onClick = {
                        if (isExecuting) return@Button
                        isExecuting = true
                        output = "Executing..."

                        scope.launch {
                             val result = when (language.lowercase()) {
                                "kotlin" -> codeExecutor.executeKotlinCode(code, stdin)
                                "javascript", "js", "nodejs" -> codeExecutor.executeJavaScriptCode(code, stdin)
                                "python", "python3" -> codeExecutor.executePythonCode(code, stdin)
                                "java" -> codeExecutor.executeJavaCode(code, stdin)
                                "sql", "sqlite" -> codeExecutor.executeSqlCode(code, stdin)
                                else -> CodeExecutionResult.Error("Unsupported language: $language")
                            }

                            output = when (result) {
                                is CodeExecutionResult.Success -> result.output
                                is CodeExecutionResult.Error -> "Error: ${result.message}"
                            }
                            isExecuting = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isExecuting
                ) {
                    if (isExecuting) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Run Code")
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
                            .weight(0.6f),
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
