package com.yodgorbek.codelearnapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yodgorbek.codelearnapp.domain.model.Course
import com.yodgorbek.codelearnapp.domain.model.CourseCategory

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val progress: Float,
    val totalLessons: Int,
    val completedLessons: Int,
    val category: String,
    val isBookmarked: Boolean = false
) {
    fun toDomain(): Course = Course(
        id = id,
        title = title,
        description = description,
        icon = icon,
        progress = progress,
        totalLessons = totalLessons,
        completedLessons = completedLessons,
        category = CourseCategory.valueOf(category)
    )
}

fun Course.toEntity(): CourseEntity = CourseEntity(
    id = id,
    title = title,
    description = description,
    icon = icon,
    progress = progress,
    totalLessons = totalLessons,
    completedLessons = completedLessons,
    category = category.name
)
