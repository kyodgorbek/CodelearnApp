package com.example.codelearnapp.presentation.lesson

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelearnapp.presentation.components.VideoPlayer
import com.example.codelearnapp.presentation.components.CodeEditor
import com.example.codelearnapp.domain.model.LessonType
import com.example.codelearnapp.domain.model.Quiz
import com.airbnb.lottie.compose.*
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
                title = { 
                    Column {
                        Text(
                            state.lesson?.title ?: "Lesson",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { 
                        viewModel.sendIntent(LessonIntent.BackPressed) 
                    }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Toggle bookmark */ }) {
                        Icon(Icons.Default.BookmarkBorder, "Bookmark")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
        ) {
            // Horizontal Progress Bar
            LinearProgressIndicator(
                progress = { 0.5f }, // Mock progress for now
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )

            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            } else {
                state.lesson?.let { lesson ->
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .verticalScroll(rememberScrollState())
                                .padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            // Lesson Type Badge
                            Surface(
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(12.dp),
                                border = ButtonDefaults.outlinedButtonBorder.copy(
                                    brush = Brush.linearGradient(
                                        listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)
                                    )
                                )
                            ) {
                                Text(
                                    text = lesson.type.name,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            
                            // Video Player
                            lesson.videoUrl?.let { url ->
                                VideoPlayer(
                                    url = url,
                                    autoPlay = state.autoPlayVideo
                                )
                            }
                            
                            // Content
                            Text(
                                text = lesson.content,
                                style = MaterialTheme.typography.bodyLarge,
                                lineHeight = 28.sp,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            
                            // Code Section (Theory Example or Interactive Practice)
                            lesson.codeExample?.let {
                                val language = when {
                                    lesson.courseId.contains("python") || lesson.courseId.contains("data-science") -> "python"
                                    lesson.courseId.contains("kotlin") -> "kotlin"
                                    lesson.courseId.contains("java") -> "java"
                                    lesson.courseId.contains("web-dev") || lesson.courseId.contains("js") -> "javascript"
                                    else -> "kotlin"
                                }

                                Text(
                                    if (lesson.type == LessonType.CODE_PRACTICE) "Interactive Practice" else "Code Example",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                
                                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                    ) {
                                        CodeEditor(
                                            code = state.currentCode,
                                            onCodeChange = { viewModel.sendIntent(LessonIntent.UpdateCode(it)) },
                                            language = language,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }
                                    
                                    Button(
                                        onClick = { viewModel.sendIntent(LessonIntent.RunCode) },
                                        enabled = !state.isExecuting,
                                        modifier = Modifier.align(Alignment.End),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFF4CAF50)
                                        )
                                    ) {
                                        if (state.isExecuting) {
                                            CircularProgressIndicator(
                                                modifier = Modifier.size(18.dp),
                                                color = Color.White,
                                                strokeWidth = 2.dp
                                            )
                                        } else {
                                            Text("Run Code")
                                        }
                                    }

                                    if (state.executionOutput.isNotEmpty()) {
                                        Surface(
                                            modifier = Modifier.fillMaxWidth(),
                                            color = Color(0xFF121212),
                                            shape = RoundedCornerShape(8.dp)
                                        ) {
                                            Column(modifier = Modifier.padding(12.dp)) {
                                                Text(
                                                    "Output:",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    color = Color.Gray
                                                )
                                                Text(
                                                    state.executionOutput,
                                                    style = MaterialTheme.typography.bodySmall,
                                                    fontFamily = FontFamily.Monospace,
                                                    color = Color.White
                                                )
                                            }
                                        }
                                    }
                                }
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
                        
                        // Action Button
                        Button(
                            onClick = {
                                viewModel.sendIntent(LessonIntent.CompleteLesson)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                                .height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            enabled = when (lesson.type) {
                                LessonType.QUIZ -> state.showQuizResult && 
                                    state.selectedAnswer == lesson.quiz?.correctAnswer
                                else -> true
                            }
                        ) {
                            Text(
                                if (lesson.type == LessonType.QUIZ && !state.showQuizResult) "Check Answer" else "Complete & Continue",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        if (state.showCelebration) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.85f)),
                contentAlignment = Alignment.Center
            ) {
                // Background Confetti for Major Milestones
                if (state.isMajorMilestone) {
                    val confettiComposition by rememberLottieComposition(
                        LottieCompositionSpec.Url("https://lottie.host/c9f9571d-5369-42b7-9759-3a3411e737c3/7M6b0m9k8S.json")
                    )
                    LottieAnimation(
                        composition = confettiComposition,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                val mainComposition by rememberLottieComposition(
                    LottieCompositionSpec.Url(
                        if (state.isMajorMilestone) 
                            "https://lottie.host/76046e7f-4740-4206-8809-77567793d59e/J1L1N5Y7vF.json" // Trophy
                        else 
                            "https://lottie.host/9e419b4b-3d60-496b-88e3-0b04756574a4/kS9Y6N0E8p.json" // Character
                    )
                )
                
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(bottom = 32.dp),
                    shape = RoundedCornerShape(32.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(32.dp)
                            .fillMaxWidth()
                    ) {
                        LottieAnimation(
                            composition = mainComposition,
                            iterations = if (state.isMajorMilestone) LottieConstants.IterateForever else 1,
                            modifier = Modifier.size(if (state.isMajorMilestone) 280.dp else 240.dp)
                        )
                        
                        Text(
                            text = if (state.isMajorMilestone) "MASTER CATEGORY!" else "Lesson Complete!",
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = if (state.isMajorMilestone) Color(0xFFFFD700) else MaterialTheme.colorScheme.primary, // Gold for major
                            textAlign = TextAlign.Center
                        )
                        
                        state.milestoneReached?.let { milestone ->
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = milestone,
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Medium,
                                lineHeight = 28.sp
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        Button(
                            onClick = { viewModel.sendIntent(LessonIntent.DismissCelebration) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                "Continue".uppercase(), 
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
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