package com.example.codelearnapp.data.repository

import com.example.codelearnapp.data.local.AppDatabase
import com.example.codelearnapp.data.local.PreferencesManager
import com.example.codelearnapp.data.local.entity.toEntity
import com.example.codelearnapp.data.remote.FirebaseAuthRepository
import com.example.codelearnapp.data.remote.FirestoreRepository
import com.example.codelearnapp.domain.model.Course
import com.example.codelearnapp.domain.model.Lesson
import com.example.codelearnapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.*
import java.util.concurrent.TimeUnit

class EnhancedCourseRepositoryImpl(
    private val database: AppDatabase,
    private val preferencesManager: PreferencesManager,
    private val firestoreRepository: FirestoreRepository,
    private val authRepository: FirebaseAuthRepository
) : CourseRepository {
    
    private val courseDao = database.courseDao()
    private val lessonDao = database.lessonDao()
    private val userProgressDao = database.userProgressDao()
    private val achievementDao = database.achievementDao()
    private val bookmarkDao = database.bookmarkDao()
    
    // Initialize with mock data on first launch
    init {
        // This should be called once in Application class
        // initializeDatabase()
    }
    
    override fun getAllCourses(): Flow<List<Course>> {
        return courseDao.getAllCourses()
            .map { entities -> entities.map { it.toDomain() } }
    }
    
    override fun getCourseById(id: String): Flow<Course?> {
        return courseDao.getCourseById(id)
            .map { it?.toDomain() }
    }
    
    override fun getLessonsByCourse(courseId: String): Flow<List<Lesson>> {
        return lessonDao.getLessonsByCourse(courseId)
            .map { entities -> entities.map { it.toDomain() } }
    }
    
    override fun getLessonById(lessonId: String): Flow<Lesson?> {
        return lessonDao.getLessonById(lessonId)
            .map { it?.toDomain() }
    }
    
    override fun searchLessons(query: String): Flow<List<Lesson>> {
        return lessonDao.searchLessons(query)
            .map { entities -> entities.map { it.toDomain() } }
    }
    
    override fun getBookmarkedLessons(): Flow<List<Lesson>> {
        return lessonDao.getBookmarkedLessons()
            .map { entities -> entities.map { it.toDomain() } }
    }
    
    override suspend fun updateLessonProgress(lessonId: String, isCompleted: Boolean) {
        val timestamp = if (isCompleted) System.currentTimeMillis() else null
        lessonDao.updateCompletion(lessonId, isCompleted, timestamp)
        
        // Update course progress
        val lesson = lessonDao.getLessonById(lessonId).firstOrNull()
        lesson?.let {
            updateCourseProgress(it.courseId)
        }
        
        // Update user progress
        if (isCompleted) {
            userProgressDao.addXp(50)
            userProgressDao.incrementLessonsCompleted()
            updateStreak()
            
            // Check achievements
            checkAndUnlockAchievements()
            
            // Sync to Firebase if signed in
            if (authRepository.isSignedIn()) {
                val user = authRepository.currentUser
                user?.let { u ->
                    firestoreRepository.syncLessonProgress(u.uid, lessonId, isCompleted)
                    
                    val progress = userProgressDao.getUserProgress().firstOrNull()
                    progress?.let { p ->
                        firestoreRepository.updateUserXp(u.uid, p.totalXp)
                        firestoreRepository.updateLeaderboard(
                            u.uid,
                            u.displayName ?: u.email ?: "Anonymous",
                            p.totalXp
                        )
                    }
                }
            }
        }
    }
    
    override suspend fun toggleBookmark(lessonId: String, isBookmarked: Boolean) {
        lessonDao.updateBookmarkStatus(lessonId, isBookmarked)
        if (isBookmarked) {
            val lesson = lessonDao.getLessonById(lessonId).firstOrNull()
            lesson?.let {
                bookmarkDao.insertBookmark(
                    com.example.codelearnapp.data.local.entity.BookmarkEntity(
                        lessonId = it.id,
                        courseId = it.courseId
                    )
                )
            }
        } else {
            bookmarkDao.deleteBookmark(lessonId)
        }
    }
    
    private suspend fun updateCourseProgress(courseId: String) {
        val allLessons = lessonDao.getLessonsByCourse(courseId).firstOrNull() ?: return
        val completedCount = allLessons.count { it.isCompleted }
        val progress = if (allLessons.isNotEmpty()) {
            completedCount.toFloat() / allLessons.size.toFloat()
        } else 0f
        
        courseDao.updateProgress(courseId, progress, completedCount)
    }
    
    private suspend fun updateStreak() {
        val progress = userProgressDao.getUserProgress().firstOrNull() ?: return
        val currentTime = System.currentTimeMillis()
        val oneDayMillis = TimeUnit.DAYS.toMillis(1)
        val timeSinceLastActivity = currentTime - progress.lastActivityDate
        
        val newStreak = when {
            timeSinceLastActivity < oneDayMillis -> progress.currentStreak + 1
            timeSinceLastActivity < oneDayMillis * 2 -> progress.currentStreak
            else -> 1
        }
        
        userProgressDao.updateStreak(newStreak, currentTime)
    }
    
    private suspend fun checkAndUnlockAchievements() {
        val progress = userProgressDao.getUserProgress().firstOrNull() ?: return
        val achievements = achievementDao.getAllAchievements().firstOrNull() ?: return
        
        achievements.forEach { achievement ->
            if (!achievement.isUnlocked) {
                val shouldUnlock = when (achievement.type) {
                    "LESSONS" -> progress.lessonsCompleted >= achievement.requiredProgress
                    "XP" -> progress.totalXp >= achievement.requiredProgress
                    "STREAK" -> progress.currentStreak >= achievement.requiredProgress
                    "COURSES" -> progress.coursesCompleted >= achievement.requiredProgress
                    else -> false
                }
                
                if (shouldUnlock) {
                    achievementDao.unlockAchievement(
                        achievement.id,
                        true,
                        System.currentTimeMillis()
                    )
                } else {
                    // Update progress
                    val currentProgress = when (achievement.type) {
                        "LESSONS" -> progress.lessonsCompleted
                        "XP" -> progress.totalXp
                        "STREAK" -> progress.currentStreak
                        "COURSES" -> progress.coursesCompleted
                        else -> 0
                    }
                    achievementDao.updateProgress(achievement.id, currentProgress)
                }
            }
        }
    }
}
