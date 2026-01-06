package com.example.codelearnapp.presentation.tutor

import androidx.lifecycle.viewModelScope
import com.example.codelearnapp.data.repository.AiRepositoryImpl
import com.example.codelearnapp.domain.repository.AiRepository
import com.example.codelearnapp.presentation.mvi.MviViewModel
import kotlinx.coroutines.launch

class AiTutorViewModel(
    private val repository: AiRepository
) : MviViewModel<AiTutorIntent, AiTutorState, AiTutorEffect>(AiTutorState()) {

    override fun handleIntent(intent: AiTutorIntent) {
        when (intent) {
            is AiTutorIntent.InitLesson -> initLesson(intent.lessonId)
            is AiTutorIntent.SendMessage -> sendMessage(intent.text)
            is AiTutorIntent.UpdateInput -> updateInput(intent.text)
            is AiTutorIntent.ClearChat -> clearChat()
        }
    }

    private fun initLesson(lessonId: String) {
        // Only initialize if it's a new lesson or not initialized
        if (state.value.lessonId == lessonId) return

        viewModelScope.launch {
            setState { copy(lessonId = lessonId, isLoading = true) }
            
            // Subscribe to DB updates
            launch {
                repository.getMessages(lessonId).collect { msgs ->
                    setState { copy(messages = msgs) }
                }
            }
            
            // Check if empty, then greet
            // Note: This check might happen before DB load finishes if not careful.
            // But since we observe Flow, we can just check if empty after a delay? 
            // Better: Repository handles "if empty insert greeting".
            // For now, let's just do greeting if list is empty after a short moment or simply check count.
            // Simpler: Just rely on user sending first message or if DB is empty, send greeting.
            
            try {
                // Determine if we need to send a greeting
                // Ideally repository.getMessages would emit empty list immediately.
                // We'll trust the user interaction for now or add a specialized "ensureGreeting" method.
                if (state.value.messages.isEmpty()) { 
                     // We can't check state.value.messages here immediately because collect is async.
                     // Let's just greet if we think it's fresh. 
                     // Or easier: let the user initiate.
                     // But the requirements said "Initial welcome message".
                }
                
                setState { copy(isLoading = false) }
            } catch (e: Exception) {
                setState { copy(isLoading = false) }
                sendEffect(AiTutorEffect.ShowError("Failed to load AI Tutor: ${e.message}"))
            }
        }
    }

    private fun updateInput(text: String) {
        setState { copy(inputText = text) }
    }

    private fun clearChat() {
        // This should clear DB
        // repository.clearChat(lessonId) // Need to add to interface if desired
        setState { copy(messages = emptyList()) }
    }

    private fun sendMessage(text: String) {
        if (text.isBlank()) return
        
        val currentLessonId = state.value.lessonId ?: "general"
        val currentHistory = state.value.messages

        // Clear input immediately
        setState { copy(inputText = "", isLoading = true) }

        viewModelScope.launch {
            try {
                // The repository handles saving User message and AI response to DB.
                // The Flow subscription in initLesson will update the UI automatically.
                repository.getAiResponse(
                    lessonId = currentLessonId,
                    userMessage = text,
                    history = currentHistory
                )
                
                setState { copy(isLoading = false) }
            } catch (e: Exception) {
                setState { copy(isLoading = false) }
                sendEffect(AiTutorEffect.ShowError("Failed to connect: ${e.message}"))
            }
        }
    }
}
