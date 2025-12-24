package com.example.codelearnapp.presentation.coursedetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.codelearnapp.domain.model.Lesson
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreen(
    courseId: String,
    viewModel: CourseDetailViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
    onLessonClick: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    
    LaunchedEffect(courseId) {
        viewModel.sendIntent(CourseDetailIntent.LoadCourse(courseId))
    }
    
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is CourseDetailEffect.NavigateToLesson -> onLessonClick(effect.lessonId)
                is CourseDetailEffect.NavigateBack -> onNavigateBack()
            }
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.course?.title ?: "Course") },
                navigationIcon = {
                    IconButton(onClick = { 
                        viewModel.sendIntent(CourseDetailIntent.BackPressed) 
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        "Lessons",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                items(state.lessons) { lesson ->
                    LessonItem(
                        lesson = lesson,
                        onClick = { 
                            viewModel.sendIntent(
                                CourseDetailIntent.LessonClicked(lesson.id)
                            ) 
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LessonItem(lesson: Lesson, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = if (lesson.isCompleted) {
                    MaterialTheme.colorScheme.tertiary
                } else {
                    MaterialTheme.colorScheme.primaryContainer
                }
            ) {
                Box(contentAlignment = Alignment.Center) {
                    if (lesson.isCompleted) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = "Completed",
                            tint = MaterialTheme.colorScheme.onTertiary
                        )
                    } else {
                        Text(
                            text = lesson.order.toString(),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = lesson.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = lesson.type.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}