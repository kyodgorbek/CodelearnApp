package com.example.codelearnapp.di

import android.content.Context
import androidx.room.Room
import com.example.codelearnapp.data.local.AppDatabase
import com.example.codelearnapp.data.local.PreferencesManager
import com.example.codelearnapp.data.remote.FirebaseAuthRepository
import com.example.codelearnapp.data.remote.FirestoreRepository
import com.example.codelearnapp.data.repository.AchievementRepositoryImpl
import com.example.codelearnapp.data.repository.CourseRepositoryImpl
import com.example.codelearnapp.data.repository.EnhancedCourseRepositoryImpl
import com.example.codelearnapp.data.sync.SyncManager
import com.example.codelearnapp.domain.repository.AchievementRepository
import com.example.codelearnapp.domain.repository.CourseRepository
import com.example.codelearnapp.domain.usecase.*
import com.example.codelearnapp.presentation.achievements.AchievementsViewModel
import com.example.codelearnapp.presentation.auth.AuthViewModel
import com.example.codelearnapp.presentation.bookmarks.BookmarksViewModel
import com.example.codelearnapp.presentation.coursedetail.CourseDetailViewModel
import com.example.codelearnapp.presentation.home.HomeViewModel
import com.example.codelearnapp.presentation.leaderboard.LeaderboardViewModel
import com.example.codelearnapp.presentation.lesson.LessonViewModel
import com.example.codelearnapp.presentation.search.SearchViewModel
import com.example.codelearnapp.presentation.settings.SettingsViewModel
import com.example.codelearnapp.presentation.onboarding.OnboardingViewModel
import com.example.codelearnapp.presentation.playground.PlaygroundViewModel
import com.example.codelearnapp.domain.codeexecution.CodeExecutor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Database
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "codelearn_database"
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    // DAOs
    single { get<AppDatabase>().chatDao() }
    single { get<AppDatabase>().courseDao() }
    single { get<AppDatabase>().lessonDao() }
    single { get<AppDatabase>().userProgressDao() }
    single { get<AppDatabase>().achievementDao() }
    single { get<AppDatabase>().bookmarkDao() }

    // Firebase
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }

    // Repositories
    single { FirebaseAuthRepository(get()) }
    single { FirestoreRepository(get()) }
    single<CourseRepository> { EnhancedCourseRepositoryImpl(get(), get(), get(), get()) }
    single<AchievementRepository> { AchievementRepositoryImpl(get()) }
    single { PreferencesManager(androidContext()) }
    single { SyncManager(androidContext(), get(), get()) }
    single { com.example.codelearnapp.data.local.ReminderManager(androidContext()) }
    single { CodeExecutor() }
    
    // AI Tutor Provider strategy
    // Switch between GeminiAiProvider() and HuggingFaceAiProvider() here.
    single<com.example.codelearnapp.data.remote.ai.AiProvider> { 
        // com.example.codelearnapp.data.remote.ai.GeminiAiProvider() // Uncomment for Gemini
        com.example.codelearnapp.data.remote.ai.HuggingFaceAiProvider() // Using HF Fallback
    }

    single<com.example.codelearnapp.domain.repository.AiRepository> { 
        com.example.codelearnapp.data.repository.AiRepositoryImpl(get(), get()) 
    }

    // Use Cases
    factory { GetCoursesUseCase(get()) }
    factory { GetCourseByIdUseCase(get()) }
    factory { GetLessonsUseCase(get()) }
    factory { GetLessonByIdUseCase(get()) }
    factory { CompleteLessonUseCase(get()) }
    factory { SearchLessonsUseCase(get()) }
    factory { GetBookmarkedLessonsUseCase(get()) }
    factory { ToggleBookmarkUseCase(get()) }
    factory { GetAchievementsUseCase(get()) }
    factory { GetLeaderboardUseCase(get()) }
    factory { GetUserProgressUseCase(get()) }

    // ViewModels
    viewModel { HomeViewModel(get()) }
    viewModel { CourseDetailViewModel(get(), get()) }
    viewModel { LessonViewModel(get(), get(), get(), get(), get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { BookmarksViewModel(get(), get()) }
    viewModel { AchievementsViewModel(get()) }
    viewModel { LeaderboardViewModel(get(), get()) }
    viewModel { AuthViewModel(get(), get()) }
    viewModel { SettingsViewModel(get(), get(), get(), get(), get()) }
    viewModel { OnboardingViewModel(get()) }
    viewModel { PlaygroundViewModel(get()) }
    viewModel { com.example.codelearnapp.presentation.tutor.AiTutorViewModel(get()) }

}