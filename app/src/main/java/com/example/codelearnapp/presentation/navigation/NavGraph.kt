package com.example.codelearnapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.codelearnapp.presentation.coursedetail.CourseDetailScreen
import com.example.codelearnapp.presentation.home.HomeScreen
import com.example.codelearnapp.presentation.lesson.LessonScreen
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToCourse = { courseId ->
                    navController.navigate(Screen.CourseDetail.createRoute(courseId))
                }
            )
        }
        
        composable(
            route = Screen.CourseDetail.route,
            arguments = listOf(
                navArgument("courseId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId") ?: return@composable
            CourseDetailScreen(
                courseId = courseId,
                onNavigateBack = { navController.popBackStack() },
                onLessonClick = { lessonId ->
                    navController.navigate(Screen.Lesson.createRoute(lessonId))
                }
            )
        }
        
        composable(
            route = Screen.Lesson.route,
            arguments = listOf(
                navArgument("lessonId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId") ?: return@composable
            LessonScreen(
                lessonId = lessonId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}