package com.yodgorbek.codelearnapp.presentation.terms

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
fun TermsOfServiceScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Terms of Service") },
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
                text = "Terms of Service",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Last updated: January 12, 2026",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Divider()

            SectionTitle("1. Acceptance of Terms")
            SectionContent(
                "By accessing and using CodeLearn App, you accept and agree to be bound by the terms " +
                "and provision of this agreement. If you do not agree to these terms, please do not use our service."
            )

            SectionTitle("2. Description of Service")
            SectionContent(
                "CodeLearn App provides an interactive learning platform for programming and coding education. " +
                "The service includes courses, lessons, quizzes, achievements, and progress tracking features."
            )

            SectionTitle("3. User Accounts")
            SectionContent(
                "To access certain features, you must create an account. You are responsible for:\n\n" +
                "• Maintaining the confidentiality of your account credentials\n" +
                "• All activities that occur under your account\n" +
                "• Notifying us immediately of any unauthorized use\n" +
                "• Providing accurate and complete information"
            )

            SectionTitle("4. User Conduct")
            SectionContent(
                "You agree not to:\n\n" +
                "• Use the service for any illegal purpose\n" +
                "• Attempt to gain unauthorized access to our systems\n" +
                "• Interfere with or disrupt the service\n" +
                "• Upload malicious code or viruses\n" +
                "• Harass or harm other users\n" +
                "• Violate any applicable laws or regulations"
            )

            SectionTitle("5. Intellectual Property")
            SectionContent(
                "All content, features, and functionality of CodeLearn App are owned by us and are " +
                "protected by international copyright, trademark, and other intellectual property laws. " +
                "You may not copy, modify, distribute, or reverse engineer any part of our service."
            )

            SectionTitle("6. User Content")
            SectionContent(
                "You retain ownership of any content you submit. By submitting content, you grant us a " +
                "worldwide, non-exclusive, royalty-free license to use, reproduce, and display your content " +
                "in connection with the service."
            )

            SectionTitle("7. Subscriptions and Payments")
            SectionContent(
                "Some features may require a paid subscription. By purchasing a subscription, you agree to:\n\n" +
                "• Pay all applicable fees\n" +
                "• Provide accurate payment information\n" +
                "• Automatic renewal unless cancelled\n" +
                "• Our refund policy as stated separately"
            )

            SectionTitle("8. Termination")
            SectionContent(
                "We reserve the right to terminate or suspend your account at any time for violations of " +
                "these terms. You may also terminate your account at any time through the app settings."
            )

            SectionTitle("9. Disclaimers")
            SectionContent(
                "The service is provided \"as is\" without warranties of any kind. We do not guarantee that:\n\n" +
                "• The service will be uninterrupted or error-free\n" +
                "• Defects will be corrected\n" +
                "• The service is free of viruses or harmful components\n" +
                "• Results from using the service will meet your requirements"
            )

            SectionTitle("10. Limitation of Liability")
            SectionContent(
                "To the maximum extent permitted by law, we shall not be liable for any indirect, " +
                "incidental, special, consequential, or punitive damages resulting from your use of the service."
            )

            SectionTitle("11. Changes to Terms")
            SectionContent(
                "We reserve the right to modify these terms at any time. We will notify users of any " +
                "material changes. Continued use of the service after changes constitutes acceptance of the new terms."
            )

            SectionTitle("12. Governing Law")
            SectionContent(
                "These terms shall be governed by and construed in accordance with applicable laws, " +
                "without regard to conflict of law provisions."
            )

            SectionTitle("13. Contact Information")
            SectionContent(
                "For questions about these Terms of Service, please contact us at:\n\n" +
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
