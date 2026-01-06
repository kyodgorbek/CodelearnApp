package com.example.codelearnapp.presentation.achievements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.example.codelearnapp.data.local.entity.AchievementEntity
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.res.stringResource
import com.example.codelearnapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementsScreen(
    viewModel: AchievementsViewModel = koinViewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Achievements") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.achievements) { achievement ->
                AchievementItem(achievement)
            }
        }
    }
}

@Composable
fun getAchievementTitle(id: String, fallback: String): String {
    return when(id) {
        "ach_first_steps" -> stringResource(R.string.ach_first_steps_title)
        "ach_scholar" -> stringResource(R.string.ach_scholar_title)
        "ach_on_fire" -> stringResource(R.string.ach_on_fire_title)
        "ach_dedicated" -> stringResource(R.string.ach_dedicated_title)
        "ach_course_master" -> stringResource(R.string.ach_course_master_title)
        else -> fallback
    }
}

@Composable
fun getAchievementDesc(id: String, fallback: String): String {
    return when(id) {
        "ach_first_steps" -> stringResource(R.string.ach_first_steps_desc)
        "ach_scholar" -> stringResource(R.string.ach_scholar_desc)
        "ach_on_fire" -> stringResource(R.string.ach_on_fire_desc)
        "ach_dedicated" -> stringResource(R.string.ach_dedicated_desc)
        "ach_course_master" -> stringResource(R.string.ach_course_master_desc)
        else -> fallback
    }
}

@Composable
fun AchievementItem(achievement: AchievementEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (achievement.isUnlocked) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon/Emoji
            Surface(
                modifier = Modifier.size(56.dp),
                shape = MaterialTheme.shapes.medium,
                color = if (achievement.isUnlocked) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surface
                }
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = achievement.icon,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = getAchievementTitle(achievement.id, achievement.title),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = getAchievementDesc(achievement.id, achievement.description),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                if (!achievement.isUnlocked) {
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = {
                            achievement.currentProgress.toFloat() / achievement.requiredProgress
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "${achievement.currentProgress}/${achievement.requiredProgress}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            if (achievement.isUnlocked) {
                Surface(
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "✓ Unlocked",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
        }
    }
}