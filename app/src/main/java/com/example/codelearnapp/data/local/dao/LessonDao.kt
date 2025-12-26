package com.example.codelearnapp.data.local.dao

import androidx.room.*
import com.example.codelearnapp.data.local.entity.LessonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {
    @Query("SELECT * FROM lessons WHERE courseId = :courseId ORDER BY `order` ASC")
    fun getLessonsByCourse(courseId: String): Flow<List<LessonEntity>>
    
    @Query("SELECT * FROM lessons WHERE id = :lessonId")
    fun getLessonById(lessonId: String): Flow<LessonEntity?>
    
    @Query("SELECT * FROM lessons WHERE isBookmarked = 1 ORDER BY title ASC")
    fun getBookmarkedLessons(): Flow<List<LessonEntity>>
    
    @Query("SELECT * FROM lessons WHERE isCompleted = 1 ORDER BY completedAt DESC")
    fun getCompletedLessons(): Flow<List<LessonEntity>>
    
    @Query("SELECT * FROM lessons WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    fun searchLessons(query: String): Flow<List<LessonEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: LessonEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLessons(lessons: List<LessonEntity>)
    
    @Query("UPDATE lessons SET isCompleted = :isCompleted, completedAt = :completedAt WHERE id = :lessonId")
    suspend fun updateCompletion(lessonId: String, isCompleted: Boolean, completedAt: Long?)
    
    @Query("UPDATE lessons SET isBookmarked = :isBookmarked WHERE id = :lessonId")
    suspend fun updateBookmarkStatus(lessonId: String, isBookmarked: Boolean)
    
    @Query("DELETE FROM lessons")
    suspend fun deleteAllLessons()
}
