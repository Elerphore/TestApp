package ru.elerphore.testapplication.api.dto

import ru.elerphore.testapplication.db.entity.ReviewDBEntity

data class ReviewEntity(
    val image: String,

    val title: String,
)

fun ReviewEntity.dbEntity() = ReviewDBEntity(image = image, title = title)