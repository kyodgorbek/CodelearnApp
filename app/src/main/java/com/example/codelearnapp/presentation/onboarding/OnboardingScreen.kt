package com.example.codelearnapp.presentation.onboarding
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = koinViewModel(),
    onFinish: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    var currentStep by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()

    // 0: Welcome
    // 1: Motivation
    // 2: Role
    // 3: Interest Type
    // 4: Interest Topic
    // 5: Experience
    // 6: Career Path (Recommendation)
    // 7: Daily Goal
    // 8: Reminder

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Progress Bar (skip on welcome)
            if (currentStep > 0) {
                LinearProgressIndicator(
                    progress = { currentStep / 8f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.primaryContainer,
                )
            }

            AnimatedContent(
                targetState = currentStep,
                label = "onboarding_steps",
                modifier = Modifier.weight(1f)
            ) { step ->
                when (step) {
                    0 -> WelcomeStep()
                    1 -> QuestionStep(
                        question = "Why are you learning to code?",
                        options = listOf(
                            OptionItem("career", "Become a professional developer", "💼"),
                            OptionItem("fun", "Just for fun", "🎮"),
                            OptionItem("skill", "Improve my current job skills", "📈"),
                            OptionItem("project", "Build a specific project", "🚀")
                        ),
                        selectedId = state.motivation,
                        onOptionSelected = { viewModel.updateMotivation(it) }
                    )
                    2 -> QuestionStep(
                        question = "Which of these describes you best?",
                        options = listOf(
                            OptionItem("student_hs", "High school student", "🎒"),
                            OptionItem("student_uni", "University student", "🎓"),
                            OptionItem("employee", "Employee", "💼"),
                            OptionItem("self_employed", "Self-employed", "💻"),
                            OptionItem("other", "None of these", "🌿")
                        ),
                        selectedId = state.role,
                        onOptionSelected = { viewModel.updateRole(it) }
                    )
                    3 -> QuestionStep(
                        question = "Which aspect of coding captivates you?",
                        options = listOf(
                            OptionItem("visual", "How things look (appearance)", "🖼️"),
                            OptionItem("logic", "How things work (logic)", "⚙️"),
                            OptionItem("both", "I'm intrigued by both", "✨")
                        ),
                        selectedId = state.interestType,
                        onOptionSelected = { viewModel.updateInterestType(it) }
                    )
                    4 -> QuestionStep(
                        question = "What do you find the most interesting?",
                        options = listOf(
                            OptionItem("web", "Web apps", "🌐"),
                            OptionItem("games", "Games", "👾"),
                            OptionItem("data", "Data science", "📊"),
                            OptionItem("ai", "AI / Machine learning", "🤖"),
                            OptionItem("auto", "Automating tasks", "⚡")
                        ),
                        selectedId = state.interestTopic,
                        onOptionSelected = { viewModel.updateInterestTopic(it) }
                    )
                    5 -> QuestionStep(
                        question = "How much coding experience do you have?",
                        options = listOf(
                            OptionItem("none", "None", "🌱"),
                            OptionItem("basic", "A little bit", "🌿"),
                            OptionItem("intermediate", "I know the basics", "🌳")
                        ),
                        selectedId = state.experience,
                        onOptionSelected = { viewModel.updateExperience(it) }
                    )
                    6 -> PathSelectionStep(
                        recommendedPath = state.recommendedPath,
                        selectedId = state.careerPath,
                        onOptionSelected = { viewModel.updateCareerPath(it) }
                    )
                    7 -> QuestionStep(
                        question = "How much time do you want to spend learning?",
                        options = listOf(
                            OptionItem("5", "Casual (5 min/day)", "☕"),
                            OptionItem("10", "Regular (10 min/day)", "📚"),
                            OptionItem("20", "Serious (20 min/day)", "🚀")
                        ),
                        selectedId = state.dailyGoal.toString(),
                        onOptionSelected = { viewModel.updateDailyGoal(it.toIntOrNull() ?: 10) }
                    )
                    8 -> ReminderStep(
                        onTimeSelected = { viewModel.updateReminderTime(it) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (currentStep < 8) {
                        currentStep++
                    } else {
                        viewModel.completeOnboarding()
                        onFinish()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                enabled = when (currentStep) {
                    1 -> state.motivation.isNotEmpty()
                    2 -> state.role.isNotEmpty()
                    3 -> state.interestType.isNotEmpty()
                    4 -> state.interestTopic.isNotEmpty()
                    5 -> state.experience.isNotEmpty()
                    6 -> state.careerPath.isNotEmpty()
                    7 -> state.dailyGoal > 0 
                    else -> true
                }
            ) {
                Text(
                    text = if (currentStep == 0) "Let's go" else if (currentStep == 8) "Yes, turn on" else "Continue",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            if (currentStep == 8) {
                 TextButton(
                    onClick = {
                        viewModel.completeOnboarding()
                        onFinish()
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Set later", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

// Reuse WelcomeStep, QuestionStep, ReminderStep, SelectionCard...
// Add PathSelectionStep logic

@Composable
fun PathSelectionStep(
    recommendedPath: String,
    selectedId: String,
    onOptionSelected: (String) -> Unit
) {
     Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "What do you want to learn?",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Text(
            text = "You can switch paths at any time.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        val paths = listOf(
            OptionItem("web", "Full-Stack Developer", "🌐"),
            OptionItem("python", "Python Developer", "🐍"),
            OptionItem("mobile", "Mobile Developer", "📱")
        )

        LazyColumn {
            items(paths) { option ->
                val isRecommended = option.id == recommendedPath
                
                if (isRecommended) {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        modifier = Modifier.padding(bottom = 4.dp).align(Alignment.End)
                    ) {
                        Text(
                            "RECOMMENDED",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                }
                
                SelectionCard(
                    option = option,
                    isSelected = option.id == selectedId,
                    onClick = { onOptionSelected(option.id) }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun WelcomeStep() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App Icon
        androidx.compose.foundation.Image(
            painter = androidx.compose.ui.res.painterResource(id = com.example.codelearnapp.R.mipmap.ic_launcher_round),
            contentDescription = "App Icon",
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(24.dp))
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "Welcome to Codelearn!",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "To build your personal curriculum, we'll ask you a few questions.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}

@Composable
fun QuestionStep(
    question: String,
    options: List<OptionItem>,
    selectedId: String?,
    onOptionSelected: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = question,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        
        LazyColumn {
            items(options) { option ->
                SelectionCard(
                    option = option,
                    isSelected = option.id == selectedId,
                    onClick = { onOptionSelected(option.id) }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun ReminderStep(onTimeSelected: (String) -> Unit) {
    // Simplified reminder visual for now
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Build a Habit",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Learning a little every day is the key to success. We'll remind you to practice.",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(48.dp))
        
        // Mock Time Picker Visual
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "18:00",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(vertical = 32.dp, horizontal = 48.dp)
            )
        }
    }
}

@Composable
fun SelectionCard(
    option: OptionItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        border = if (isSelected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) 
                           else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = option.icon,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text = option.text,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

data class OptionItem(val id: String, val text: String, val icon: String)
