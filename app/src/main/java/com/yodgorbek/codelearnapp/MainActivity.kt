package com.yodgorbek.codelearnapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.yodgorbek.codelearnapp.ui.theme.CodelearnAppTheme
import com.yodgorbek.codelearnapp.presentation.navigation.NavGraph
import com.yodgorbek.codelearnapp.data.local.PreferencesManager
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.yodgorbek.codelearnapp.data.remote.FirebaseAuthRepository
import org.koin.android.ext.android.inject
import java.util.Locale

class MainActivity : ComponentActivity() {
    private val preferencesManager: PreferencesManager by inject()
    private val authRepository: FirebaseAuthRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkMode by preferencesManager.isDarkMode.collectAsState(initial = false)
            val selectedLanguage by preferencesManager.selectedLanguage.collectAsState(initial = "en")

            // Apply language when it changes
            LaunchedEffect(selectedLanguage) {
                applyLanguage(selectedLanguage)
            }

            CodelearnAppTheme(darkTheme = isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val isOnboardingCompleted by preferencesManager.isOnboardingCompleted.collectAsState(initial = true)
                    val isSignedIn = authRepository.isSignedIn()

                    NavGraph(
                        navController = navController,
                        isOnboardingCompleted = isOnboardingCompleted,
                        isUserLoggedIn = isSignedIn
                    )
                }
            }
        }
    }

    private fun applyLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(resources.configuration)
        config.setLocale(locale)

        @Suppress("DEPRECATION")
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}

