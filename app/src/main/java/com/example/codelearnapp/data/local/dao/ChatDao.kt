package com.example.codelearnapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.codelearnapp.data.local.entity.ChatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Query("SELECT * FROM chat_messages WHERE lessonId = :lessonId ORDER BY timestamp ASC")
    fun getMessagesForLesson(lessonId: String): Flow<List<ChatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatEntity)

    @Query("DELETE FROM chat_messages WHERE lessonId = :lessonId")
    suspend fun clearMessages(lessonId: String)
}
