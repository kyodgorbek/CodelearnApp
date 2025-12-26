package com.example.codelearnapp.presentation.auth

import androidx.lifecycle.viewModelScope
import com.example.codelearnapp.data.remote.FirebaseAuthRepository
import com.example.codelearnapp.data.remote.FirestoreRepository
import com.example.codelearnapp.presentation.mvi.MviViewModel
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: FirebaseAuthRepository,
    private val firestoreRepository: FirestoreRepository
) : MviViewModel<AuthIntent, AuthState, AuthEffect>() {
    
    override fun createInitialState(): AuthState = AuthState(
        isSignedIn = authRepository.isSignedIn(),
        userEmail = authRepository.currentUser?.email
    )
    
    override fun handleIntent(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.SignInWithEmail -> signInWithEmail(intent.email, intent.password)
            is AuthIntent.SignUpWithEmail -> signUpWithEmail(intent.email, intent.password)
            is AuthIntent.SignInWithGoogle -> signInWithGoogle(intent.idToken)
            is AuthIntent.SignOut -> signOut()
        }
    }
    
    private fun signInWithEmail(email: String, password: String) {
        setState { copy(isLoading = true, error = null) }
        
        viewModelScope.launch {
            val result = authRepository.signInWithEmail(email, password)
            
            result.fold(
                onSuccess = { user ->
                    // Update last login timestamp
                    firestoreRepository.updateLastLogin(user.uid)
                    
                    setState {
                        copy(
                            isLoading = false,
                            isSignedIn = true,
                            userEmail = user.email
                        )
                    }
                    sendEffect(AuthEffect.ShowSuccess("Signed in successfully"))
                    sendEffect(AuthEffect.NavigateToHome)
                },
                onFailure = { error ->
                    setState {
                        copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                    sendEffect(AuthEffect.ShowError(error.message ?: "Sign in failed"))
                }
            )
        }
    }
    
    private fun signUpWithEmail(email: String, password: String) {
        setState { copy(isLoading = true, error = null) }
        
        viewModelScope.launch {
            val result = authRepository.signUpWithEmail(email, password)
            
            result.fold(
                onSuccess = { user ->
                    // Create user profile in Firestore
                    val profile = com.example.codelearnapp.domain.model.UserProfile(
                        userId = user.uid,
                        email = user.email ?: email,
                        displayName = user.displayName ?: email.substringBefore("@"),
                        photoUrl = user.photoUrl?.toString(),
                        totalXp = 0,
                        currentStreak = 0,
                        longestStreak = 0,
                        coursesCompleted = 0,
                        lessonsCompleted = 0,
                        createdAt = System.currentTimeMillis(),
                        lastLoginAt = System.currentTimeMillis()
                    )
                    firestoreRepository.saveUserProfile(user.uid, profile)
                    
                    setState {
                        copy(
                            isLoading = false,
                            isSignedIn = true,
                            userEmail = user.email
                        )
                    }
                    sendEffect(AuthEffect.ShowSuccess("Account created successfully"))
                    sendEffect(AuthEffect.NavigateToHome)
                },
                onFailure = { error ->
                    setState {
                        copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                    sendEffect(AuthEffect.ShowError(error.message ?: "Sign up failed"))
                }
            )
        }
    }
    
    private fun signInWithGoogle(idToken: String) {
        setState { copy(isLoading = true, error = null) }
        
        viewModelScope.launch {
            val result = authRepository.signInWithGoogle(idToken)
            
            result.fold(
                onSuccess = { user ->
                    // Create or update user profile in Firestore
                    val profile = com.example.codelearnapp.domain.model.UserProfile(
                        userId = user.uid,
                        email = user.email ?: "",
                        displayName = user.displayName ?: user.email?.substringBefore("@") ?: "User",
                        photoUrl = user.photoUrl?.toString(),
                        totalXp = 0,
                        currentStreak = 0,
                        longestStreak = 0,
                        coursesCompleted = 0,
                        lessonsCompleted = 0,
                        createdAt = System.currentTimeMillis(),
                        lastLoginAt = System.currentTimeMillis()
                    )
                    firestoreRepository.saveUserProfile(user.uid, profile)
                    
                    setState {
                        copy(
                            isLoading = false,
                            isSignedIn = true,
                            userEmail = user.email
                        )
                    }
                    sendEffect(AuthEffect.ShowSuccess("Signed in with Google"))
                    sendEffect(AuthEffect.NavigateToHome)
                },
                onFailure = { error ->
                    setState {
                        copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                    sendEffect(AuthEffect.ShowError(error.message ?: "Google sign in failed"))
                }
            )
        }
    }
    
    private fun signOut() {
        authRepository.signOut()
        setState {
            copy(
                isSignedIn = false,
                userEmail = null
            )
        }
        sendEffect(AuthEffect.ShowSuccess("Signed out"))
    }
}