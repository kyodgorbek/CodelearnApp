package com.yodgorbek.codelearnapp.di

import android.content.Context
import androidx.room.Room
import com.yodgorbek.codelearnapp.data.local.AppDatabase
import com.yodgorbek.codelearnapp.data.local.PreferencesManager
import com.yodgorbek.codelearnapp.data.remote.FirebaseAuthRepository
import com.yodgorbek.codelearnapp.data.remote.FirestoreRepository
import com.yodgorbek.codelearnapp.data.repository.AchievementRepositoryImpl
import com.yodgorbek.codelearnapp.data.repository.CourseRepositoryImpl
import com.yodgorbek.codelearnapp.data.repository.EnhancedCourseRepositoryImpl
import com.yodgorbek.codelearnapp.data.sync.SyncManager
import com.yodgorbek.codelearnapp.domain.repository.AchievementRepository
import com.yodgorbek.codelearnapp.domain.repository.CourseRepository
import com.yodgorbek.codelearnapp.domain.usecase.*
import com.yodgorbek.codelearnapp.presentation.achievements.AchievementsViewModel
import com.yodgorbek.codelearnapp.presentation.auth.AuthViewModel
import com.yodgorbek.codelearnapp.presentation.bookmarks.BookmarksViewModel
import com.yodgorbek.codelearnapp.presentation.coursedetail.CourseDetailViewModel
import com.yodgorbek.codelearnapp.presentation.home.HomeViewModel
import com.yodgorbek.codelearnapp.presentation.leaderboard.LeaderboardViewModel
import com.yodgorbek.codelearnapp.presentation.lesson.LessonViewModel
import com.yodgorbek.codelearnapp.presentation.search.SearchViewModel
import com.yodgorbek.codelearnapp.presentation.settings.SettingsViewModel
import com.yodgorbek.codelearnapp.presentation.onboarding.OnboardingViewModel
import com.yodgorbek.codelearnapp.presentation.playground.PlaygroundViewModel
import com.yodgorbek.codelearnapp.domain.codeexecution.CodeExecutor
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
    single { com.yodgorbek.codelearnapp.data.local.ReminderManager(androidContext()) }
    single { CodeExecutor() }

    // AI Tutor Provider strategy
    // Switch between GeminiAiProvider() and HuggingFaceAiProvider() here.
    single<com.yodgorbek.codelearnapp.data.remote.ai.AiProvider> {
        // com.example.codelearnapp.data.remote.ai.GeminiAiProvider() // Uncomment for Gemini
        com.yodgorbek.codelearnapp.data.remote.ai.GroqAiProvider() // Using HF Fallback
    }

    single<com.yodgorbek.codelearnapp.domain.repository.AiRepository> {
        com.yodgorbek.codelearnapp.data.repository.AiRepositoryImpl(get(), get())
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
    viewModel { com.yodgorbek.codelearnapp.presentation.tutor.AiTutorViewModel(get()) }

}
