package com.example.codelearnapp.di

import android.content.Context
import androidx.room.Room
import com.example.codelearnapp.data.local.AppDatabase
import com.example.codelearnapp.data.local.PreferencesManager
import com.example.codelearnapp.data.remote.FirebaseAuthRepository
import com.example.codelearnapp.data.remote.FirestoreRepository
import com.example.codelearnapp.data.repository.AchievementRepositoryImpl
import com.example.codelearnapp.data.repository.CourseRepositoryImpl
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
        ).build()
    }

    // Firebase
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }

    // Repositories
    single { FirebaseAuthRepository(get()) }
    single { FirestoreRepository(get()) }
    single<CourseRepository> { CourseRepositoryImpl() }
    single<AchievementRepository> { AchievementRepositoryImpl(get()) }
    single { PreferencesManager(androidContext()) }
    single { SyncManager(androidContext(), get(), get()) }

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

    // ViewModels
    viewModel { HomeViewModel(get()) }
    viewModel { CourseDetailViewModel(get(), get()) }
    viewModel { LessonViewModel(get(), get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { BookmarksViewModel(get(), get()) }
    viewModel { AchievementsViewModel(get()) }
    viewModel { LeaderboardViewModel(get(), get()) }
    viewModel { AuthViewModel(get(), get()) }
    viewModel { SettingsViewModel(get(), get(), get(), get()) }

}