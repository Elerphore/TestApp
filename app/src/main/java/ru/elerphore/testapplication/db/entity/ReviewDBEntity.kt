package ru.elerphore.testapplication.db.entity

import androidx.room.Entity

@Entity("Review")
data class ReviewDBEntity(
    val image: String,
    val title: String,
)
