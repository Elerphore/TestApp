package ru.elerphore.testapplication.api.dto

import android.widget.ImageView
import ru.elerphore.testapplication.db.entity.ReviewDBEntity

data class ReviewEntity(
    val image: String,

    val title: String,

    var imageView: ImageView? = null
)

fun ReviewEntity.dbEntity() = ReviewDBEntity(image = image, title = title)