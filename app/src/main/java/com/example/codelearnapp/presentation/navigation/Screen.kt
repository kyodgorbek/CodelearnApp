package com.example.codelearnapp.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object CourseDetail : Screen("course/{courseId}") {
        fun createRoute(courseId: String) = "course/$courseId"
    }
    object Lesson : Screen("lesson/{lessonId}") {
        fun createRoute(lessonId: String) = "lesson/$lessonId"
    }
    object Search : Screen("search")
    object Bookmarks : Screen("bookmarks")
    object Achievements : Screen("achievements")
    object Leaderboard : Screen("leaderboard")
    object Settings : Screen("settings")
    object Auth : Screen("auth")
}