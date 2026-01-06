package com.example.codelearnapp.presentation.auth

import com.example.codelearnapp.presentation.mvi.UiState

data class AuthState(
    val isLoading: Boolean = false,
    val isSignedIn: Boolean = false,
    val userEmail: String? = null,
    val error: String? = null
) : UiState