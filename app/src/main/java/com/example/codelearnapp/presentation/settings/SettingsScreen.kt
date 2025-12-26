package com.example.codelearnapp.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToSignIn: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SettingsEffect.NavigateToSignIn -> onNavigateToSignIn()
                is SettingsEffect.NavigateToProfile -> { /* Navigate to profile */ }
                is SettingsEffect.NavigateToPrivacyPolicy -> { /* Show privacy policy */ }
                is SettingsEffect.NavigateToTerms -> { /* Show terms */ }
                is SettingsEffect.ShowMessage -> { /* Show snackbar */ }
            }
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Account Section
            item {
                Text(
                    "Account",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            
            item {
                SettingsItem(
                    title = if (state.isSignedIn) "Signed in as ${state.userEmail}" else "Not signed in",
                    onClick = {
                        if (state.isSignedIn) {
                            viewModel.sendIntent(SettingsIntent.SignOut)
                        } else {
                            viewModel.sendIntent(SettingsIntent.NavigateToSignIn)
                        }
                    },
                    actionText = if (state.isSignedIn) "Sign Out" else "Sign In"
                )
            }
            
            // Preferences Section
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Preferences",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            
            item {
                SettingsSwitch(
                    title = "Dark Mode",
                    checked = state.isDarkMode,
                    onCheckedChange = { 
                        viewModel.sendIntent(SettingsIntent.ToggleDarkMode(it)) 
                    }
                )
            }
            
            item {
                SettingsSwitch(
                    title = "Offline Mode",
                    checked = state.isOfflineMode,
                    onCheckedChange = { 
                        viewModel.sendIntent(SettingsIntent.ToggleOfflineMode(it)) 
                    }
                )
            }
            
            item {
                SettingsSwitch(
                    title = "Notifications",
                    checked = state.notificationsEnabled,
                    onCheckedChange = { 
                        viewModel.sendIntent(SettingsIntent.ToggleNotifications(it)) 
                    }
                )
            }
            
            item {
                SettingsSwitch(
                    title = "Auto-play Videos",
                    checked = state.autoPlayVideo,
                    onCheckedChange = { 
                        viewModel.sendIntent(SettingsIntent.ToggleAutoPlayVideo(it)) 
                    }
                )
            }
            
            item {
                SettingsItem(
                    title = "Language",
                    subtitle = state.selectedLanguage.uppercase(),
                    onClick = { 
                        viewModel.sendIntent(SettingsIntent.ShowLanguageDialog) 
                    }
                )
            }
            
            item {
                SettingsItem(
                    title = "Daily Goal",
                    subtitle = "${state.dailyGoal} lessons/day",
                    onClick = { 
                        viewModel.sendIntent(SettingsIntent.ShowDailyGoalDialog) 
                    }
                )
            }
            
            // Data Section
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Data",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            
            item {
                SettingsItem(
                    title = "Sync Data",
                    onClick = { 
                        viewModel.sendIntent(SettingsIntent.SyncData) 
                    }
                )
            }
            
            // About Section
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "About",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            
            item {
                SettingsItem(
                    title = "Privacy Policy",
                    onClick = { 
                        viewModel.sendIntent(SettingsIntent.ShowPrivacyPolicy) 
                    }
                )
            }
            
            item {
                SettingsItem(
                    title = "Terms of Service",
                    onClick = { 
                        viewModel.sendIntent(SettingsIntent.ShowTerms) 
                    }
                )
            }
        }
        
        // Language Dialog
        if (state.showLanguageDialog) {
            AlertDialog(
                onDismissRequest = { 
                    viewModel.sendIntent(SettingsIntent.DismissLanguageDialog) 
                },
                title = { Text("Select Language") },
                text = {
                    Column {
                        listOf("en", "es", "fr", "de", "ja").forEach { lang ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = state.selectedLanguage == lang,
                                    onClick = {
                                        viewModel.sendIntent(SettingsIntent.SelectLanguage(lang))
                                        viewModel.sendIntent(SettingsIntent.DismissLanguageDialog)
                                    }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(lang.uppercase())
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = { 
                            viewModel.sendIntent(SettingsIntent.DismissLanguageDialog) 
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
        
        // Daily Goal Dialog
        if (state.showDailyGoalDialog) {
            var goalText by remember { mutableStateOf(state.dailyGoal.toString()) }
            AlertDialog(
                onDismissRequest = { 
                    viewModel.sendIntent(SettingsIntent.DismissDailyGoalDialog) 
                },
                title = { Text("Set Daily Goal") },
                text = {
                    OutlinedTextField(
                        value = goalText,
                        onValueChange = { if (it.all { char -> char.isDigit() }) goalText = it },
                        label = { Text("Lessons per day") },
                        singleLine = true
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            goalText.toIntOrNull()?.let { goal ->
                                viewModel.sendIntent(SettingsIntent.SetDailyGoal(goal))
                            }
                            viewModel.sendIntent(SettingsIntent.DismissDailyGoalDialog)
                        }
                    ) {
                        Text("Set")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { 
                            viewModel.sendIntent(SettingsIntent.DismissDailyGoalDialog) 
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Composable
fun SettingsItem(
    title: String,
    subtitle: String? = null,
    onClick: () -> Unit,
    actionText: String? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            if (actionText != null) {
                TextButton(onClick = onClick) {
                    Text(actionText)
                }
            }
        }
    }
}

@Composable
fun SettingsSwitch(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

