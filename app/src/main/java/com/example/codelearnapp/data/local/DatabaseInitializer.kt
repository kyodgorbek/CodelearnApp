package com.example.codelearnapp.data.local

import com.example.codelearnapp.data.local.entity.AchievementEntity
import com.example.codelearnapp.data.local.entity.UserProgressEntity
import com.example.codelearnapp.data.local.entity.toEntity
import com.example.codelearnapp.data.repository.CourseRepositoryImpl
import kotlinx.coroutines.flow.firstOrNull

object DatabaseInitializer {
    
    suspend fun initializeDatabase(database: AppDatabase) {
        // Initialize user progress if empty
        val existingProgress = database.userProgressDao().getUserProgress().firstOrNull()
        if (existingProgress == null) {
            database.userProgressDao().insertProgress(
                UserProgressEntity(
                    userId = "local_user",
                    totalXp = 0,
                    currentStreak = 0,
                    longestStreak = 0,
                    coursesCompleted = 0,
                    lessonsCompleted = 0
                )
            )
        }
        
        // Initialize achievements if empty
        val existingAchievements = database.achievementDao().getAllAchievements().firstOrNull()
        if (existingAchievements.isNullOrEmpty()) {
            database.achievementDao().insertAchievements(getInitialAchievements())
        }

        // Always ensure courses and lessons are up to date with mock data
        val courses = CourseRepositoryImpl.getMockCourses().map { it.toEntity() }
        database.courseDao().insertCourses(courses)

        val lessons = CourseRepositoryImpl.getMockLessons().map { it.toEntity() }
        database.lessonDao().insertLessons(lessons)
    }
    
    private fun getInitialAchievements() = listOf(
        AchievementEntity(
            id = "first_lesson",
            title = "First Steps",
            description = "Complete your first lesson",
            icon = "🎯",
            requiredProgress = 1,
            type = "LESSONS"
        ),
        AchievementEntity(
            id = "five_lessons",
            title = "Getting Started",
            description = "Complete 5 lessons",
            icon = "📚",
            requiredProgress = 5,
            type = "LESSONS"
        ),
        AchievementEntity(
            id = "ten_lessons",
            title = "Dedicated Learner",
            description = "Complete 10 lessons",
            icon = "🌟",
            requiredProgress = 10,
            type = "LESSONS"
        ),
        AchievementEntity(
            id = "first_course",
            title = "Course Master",
            description = "Complete your first course",
            icon = "🏆",
            requiredProgress = 1,
            type = "COURSES"
        ),
        AchievementEntity(
            id = "streak_3",
            title = "Consistent",
            description = "3 day learning streak",
            icon = "🔥",
            requiredProgress = 3,
            type = "STREAK"
        ),
        AchievementEntity(
            id = "streak_7",
            title = "Week Warrior",
            description = "7 day learning streak",
            icon = "⚡",
            requiredProgress = 7,
            type = "STREAK"
        ),
        AchievementEntity(
            id = "streak_30",
            title = "Month Master",
            description = "30 day learning streak",
            icon = "💎",
            requiredProgress = 30,
            type = "STREAK"
        ),
        AchievementEntity(
            id = "xp_100",
            title = "XP Hunter",
            description = "Earn 100 XP",
            icon = "💫",
            requiredProgress = 100,
            type = "XP"
        ),
        AchievementEntity(
            id = "xp_500",
            title = "XP Collector",
            description = "Earn 500 XP",
            icon = "✨",
            requiredProgress = 500,
            type = "XP"
        ),
        AchievementEntity(
            id = "xp_1000",
            title = "XP Legend",
            description = "Earn 1000 XP",
            icon = "🌠",
            requiredProgress = 1000,
            type = "XP"
        )
    )
}