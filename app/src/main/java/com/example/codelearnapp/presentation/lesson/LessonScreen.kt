package com.example.codelearnapp.presentation.lesson

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.codelearnapp.domain.model.LessonType
import com.example.codelearnapp.domain.model.Quiz
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonScreen(
    lessonId: String,
    viewModel: LessonViewModel = koinViewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    
    LaunchedEffect(lessonId) {
        viewModel.sendIntent(LessonIntent.LoadLesson(lessonId))
    }
    
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is LessonEffect.NavigateBack -> onNavigateBack()
                is LessonEffect.ShowCompletionCelebration -> { /* Show animation */ }
                is LessonEffect.ShowError -> { /* Handle error */ }
            }
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.lesson?.title ?: "Lesson") },
                navigationIcon = {
                    IconButton(onClick = { 
                        viewModel.sendIntent(LessonIntent.BackPressed) 
                    }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            state.lesson?.let { lesson ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Lesson Type Badge
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = lesson.type.name,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                        
                        // Content
                        Text(
                            text = lesson.content,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        
                        // Code Example
                        lesson.codeExample?.let { code ->
                            CodeBlock(code)
                        }
                        
                        // Quiz
                        if (lesson.type == LessonType.QUIZ && lesson.quiz != null) {
                            QuizSection(
                                quiz = lesson.quiz,
                                selectedAnswer = state.selectedAnswer,
                                showResult = state.showQuizResult,
                                onAnswerSelected = { index ->
                                    viewModel.sendIntent(LessonIntent.AnswerQuiz(index))
                                }
                            )
                        }
                    }
                    
                    // Complete Button
                    Button(
                        onClick = {
                            viewModel.sendIntent(LessonIntent.CompleteLesson)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        enabled = when (lesson.type) {
                            LessonType.QUIZ -> state.showQuizResult && 
                                state.selectedAnswer == lesson.quiz?.correctAnswer
                            else -> true
                        }
                    ) {
                        Text("Complete Lesson")
                    }
                }
            }
        }
    }
}

@Composable
fun CodeBlock(code: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFF1E1E1E),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = code,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = FontFamily.Monospace,
            color = Color(0xFFDCDCDC)
        )
    }
}

@Composable
fun QuizSection(
    quiz: Quiz,
    selectedAnswer: Int?,
    showResult: Boolean,
    onAnswerSelected: (Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Text(
                text = quiz.question,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        
        quiz.options.forEachIndexed { index, option ->
            QuizOption(
                option = option,
                index = index,
                isSelected = selectedAnswer == index,
                showResult = showResult,
                isCorrect = index == quiz.correctAnswer,
                onClick = { if (!showResult) onAnswerSelected(index) }
            )
        }
        
        if (showResult) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (selectedAnswer == quiz.correctAnswer) {
                        Color(0xFF4CAF50).copy(alpha = 0.2f)
                    } else {
                        Color(0xFFF44336).copy(alpha = 0.2f)
                    }
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = if (selectedAnswer == quiz.correctAnswer) {
                            "✓ Correct!"
                        } else {
                            "✗ Incorrect"
                        },
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (selectedAnswer == quiz.correctAnswer) {
                            Color(0xFF4CAF50)
                        } else {
                            Color(0xFFF44336)
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = quiz.explanation,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun QuizOption(
    option: String,
    index: Int,
    isSelected: Boolean,
    showResult: Boolean,
    isCorrect: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        showResult && isCorrect -> Color(0xFF4CAF50).copy(alpha = 0.2f)
        showResult && isSelected && !isCorrect -> Color(0xFFF44336).copy(alpha = 0.2f)
        isSelected -> MaterialTheme.colorScheme.primaryContainer
        else -> MaterialTheme.colorScheme.surface
    }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = if (isSelected) ButtonDefaults.outlinedButtonBorder else null
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(32.dp),
                shape = RoundedCornerShape(16.dp),
                color = if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                }
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = ('A' + index).toString(),
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        },
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = option,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}