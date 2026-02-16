package com.yodgorbek.codelearnapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "chat_messages")
data class ChatEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val lessonId: String,
    val text: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)
