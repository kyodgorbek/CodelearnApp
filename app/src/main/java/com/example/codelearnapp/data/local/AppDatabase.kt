package com.example.codelearnapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.codelearnapp.data.local.dao.*
import com.example.codelearnapp.data.local.entity.*

@Database(
    entities = [
        CourseEntity::class,
        LessonEntity::class,
        UserProgressEntity::class,
        AchievementEntity::class,
        BookmarkEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun lessonDao(): LessonDao
    abstract fun userProgressDao(): UserProgressDao
    abstract fun achievementDao(): AchievementDao
    abstract fun bookmarkDao(): BookmarkDao
}
