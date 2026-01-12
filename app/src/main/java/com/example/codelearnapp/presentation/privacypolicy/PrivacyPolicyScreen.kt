package com.example.codelearnapp.presentation.privacypolicy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Privacy Policy") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Privacy Policy",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Last updated: January 12, 2026",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Divider()
            
            SectionTitle("1. Information We Collect")
            SectionContent(
                "We collect information that you provide directly to us, including:\n\n" +
                "• Account information (email address, username)\n" +
                "• Learning progress and achievements\n" +
                "• Course preferences and bookmarks\n" +
                "• Device information and usage statistics"
            )
            
            SectionTitle("2. How We Use Your Information")
            SectionContent(
                "We use the information we collect to:\n\n" +
                "• Provide, maintain, and improve our services\n" +
                "• Personalize your learning experience\n" +
                "• Track your progress and achievements\n" +
                "• Send you notifications about your learning goals\n" +
                "• Communicate with you about updates and features"
            )
            
            SectionTitle("3. Data Storage and Security")
            SectionContent(
                "We take reasonable measures to protect your information from unauthorized access, " +
                "alteration, disclosure, or destruction. Your data is stored securely using industry-standard " +
                "encryption methods."
            )
            
            SectionTitle("4. Data Sharing")
            SectionContent(
                "We do not sell, trade, or rent your personal information to third parties. " +
                "We may share aggregated, anonymized data for analytics purposes."
            )
            
            SectionTitle("5. Your Rights")
            SectionContent(
                "You have the right to:\n\n" +
                "• Access your personal data\n" +
                "• Request correction of your data\n" +
                "• Request deletion of your account and data\n" +
                "• Opt-out of notifications\n" +
                "• Export your learning data"
            )
            
            SectionTitle("6. Offline Mode")
            SectionContent(
                "When using offline mode, your data is stored locally on your device. " +
                "This data will be synchronized with our servers when you reconnect to the internet."
            )
            
            SectionTitle("7. Children's Privacy")
            SectionContent(
                "Our service is not intended for children under 13 years of age. " +
                "We do not knowingly collect personal information from children under 13."
            )
            
            SectionTitle("8. Changes to This Policy")
            SectionContent(
                "We may update this Privacy Policy from time to time. We will notify you of any changes " +
                "by posting the new Privacy Policy on this page and updating the \"Last updated\" date."
            )
            
            SectionTitle("9. Contact Us")
            SectionContent(
                "If you have any questions about this Privacy Policy, please contact us at:\n\n" +
                "Email: kyodgorbek@gmail.com"
            )
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Composable
private fun SectionContent(content: String) {
    Text(
        text = content,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
