package com.example.codelearnapp.data.local.dao

import androidx.room.*
import com.example.codelearnapp.data.local.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmarks ORDER BY bookmarkedAt DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>
    
    @Query("SELECT * FROM bookmarks WHERE lessonId = :lessonId")
    fun getBookmark(lessonId: String): Flow<BookmarkEntity?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity)
    
    @Query("DELETE FROM bookmarks WHERE lessonId = :lessonId")
    suspend fun deleteBookmark(lessonId: String)
    
    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE lessonId = :lessonId)")
    suspend fun isBookmarked(lessonId: String): Boolean
}