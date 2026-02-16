package com.yodgorbek.codelearnapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yodgorbek.codelearnapp.domain.model.Lesson
import com.yodgorbek.codelearnapp.domain.model.LessonType
import com.yodgorbek.codelearnapp.domain.model.Quiz
import com.google.gson.Gson

@Entity(tableName = "lessons")
data class LessonEntity(
    @PrimaryKey
    val id: String,
    val courseId: String,
    val title: String,
    val content: String,
    val type: String,
    val order: Int,
    val isCompleted: Boolean,
    val codeExample: String?,
    val quizJson: String?,
    val isBookmarked: Boolean = false,
    val completedAt: Long? = null,
    val videoUrl: String? = null
) {
    fun toDomain(): Lesson {
        val quiz = if (quizJson != null) {
            Gson().fromJson(quizJson, Quiz::class.java)
        } else null

        return Lesson(
            id = id,
            courseId = courseId,
            title = title,
            content = content,
            type = LessonType.valueOf(type),
            order = order,
            isCompleted = isCompleted,
            codeExample = codeExample,
            quiz = quiz,
            videoUrl = videoUrl
        )
    }
}

fun Lesson.toEntity(): LessonEntity = LessonEntity(
    id = id,
    courseId = courseId,
    title = title,
    content = content,
    type = type.name,
    order = order,
    isCompleted = isCompleted,
    codeExample = codeExample,
    quizJson = quiz?.let { Gson().toJson(it) },
    videoUrl = videoUrl
)
