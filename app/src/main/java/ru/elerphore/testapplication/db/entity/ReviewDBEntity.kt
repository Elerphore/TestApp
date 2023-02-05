package ru.elerphore.testapplication.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.elerphore.testapplication.api.dto.ReviewEntity

@Entity("Review")
data class ReviewDBEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    val image: String,

    val title: String,
)

fun ReviewDBEntity.dtoEntity() = ReviewEntity(image, title)
