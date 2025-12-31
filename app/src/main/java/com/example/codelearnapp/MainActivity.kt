package com.example.codelearnapp

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.codelearnapp.ui.theme.CodelearnAppTheme
import com.example.codelearnapp.presentation.navigation.NavGraph
import com.example.codelearnapp.data.local.PreferencesManager
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val preferencesManager: PreferencesManager by inject()
    private val authRepository: FirebaseAuthRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodelearnAppTheme {
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
}

