package com.example.codelearnapp.presentation.auth

import com.example.codelearnapp.presentation.mvi.UiIntent

sealed class AuthIntent : UiIntent {
    data class SignInWithEmail(val email: String, val password: String) : AuthIntent()
    data class SignUpWithEmail(val email: String, val password: String) : AuthIntent()
    data class SignInWithGoogle(val idToken: String) : AuthIntent()
    object SignOut : AuthIntent()
}