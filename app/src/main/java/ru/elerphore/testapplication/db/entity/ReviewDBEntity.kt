package ru.elerphore.testapplication.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Review")
data class ReviewDBEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val image: String,

    val title: String,
)
