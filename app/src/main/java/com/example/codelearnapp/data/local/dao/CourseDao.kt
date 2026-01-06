package com.example.codelearnapp.data.local.dao

import androidx.room.*
import com.example.codelearnapp.data.local.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses ORDER BY title ASC")
    fun getAllCourses(): Flow<List<CourseEntity>>
    
    @Query("SELECT * FROM courses WHERE id = :courseId")
    fun getCourseById(courseId: String): Flow<CourseEntity?>
    
    @Query("SELECT * FROM courses WHERE isBookmarked = 1")
    fun getBookmarkedCourses(): Flow<List<CourseEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: CourseEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(courses: List<CourseEntity>)
    
    @Update
    suspend fun updateCourse(course: CourseEntity)
    
    @Query("UPDATE courses SET isBookmarked = :isBookmarked WHERE id = :courseId")
    suspend fun updateBookmarkStatus(courseId: String, isBookmarked: Boolean)
    
    @Query("UPDATE courses SET progress = :progress, completedLessons = :completedLessons WHERE id = :courseId")
    suspend fun updateProgress(courseId: String, progress: Float, completedLessons: Int)
    
    @Query("DELETE FROM courses")
    suspend fun deleteAllCourses()
}