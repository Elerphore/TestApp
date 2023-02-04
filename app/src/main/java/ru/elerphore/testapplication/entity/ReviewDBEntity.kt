package ru.elerphore.testapplication.entity

import androidx.room.Entity

@Entity
data class ReviewDBEntity(
    val id: Int,
    val image: String,
    val title: String,
)
