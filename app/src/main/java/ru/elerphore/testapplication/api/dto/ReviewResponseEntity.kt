package ru.elerphore.testapplication.api.dto

data class ReviewResponseEntity(
    val raitings: Map<Int, ReviewEntity>
)
