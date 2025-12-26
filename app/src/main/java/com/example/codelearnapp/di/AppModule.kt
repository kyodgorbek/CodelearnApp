package com.example.codelearnapp.di

import com.example.codelearnapp.data.repository.CourseRepositoryImpl
import com.example.codelearnapp.domain.repository.CourseRepository
import com.example.codelearnapp.domain.usecase.*
import com.example.codelearnapp.presentation.coursedetail.CourseDetailViewModel
import com.example.codelearnapp.presentation.home.HomeViewModel
import com.example.codelearnapp.presentation.lesson.LessonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Repository
    single<CourseRepository> { CourseRepositoryImpl() }

    // Use Cases
    factory { GetCoursesUseCase(get()) }
    factory { GetCourseByIdUseCase(get()) }
    factory { GetLessonsUseCase(get()) }
    factory { GetLessonByIdUseCase(get()) }
    factory { CompleteLessonUseCase(get()) }

    // ViewModels
    viewModel { HomeViewModel(get()) }
    viewModel { CourseDetailViewModel(get(), get()) }
    viewModel { LessonViewModel(get(), get()) }

}