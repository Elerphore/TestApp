package ru.elerphore.testapplication.api

import retrofit2.Call
import retrofit2.http.GET
import ru.elerphore.testapplication.api.data.ReviewEntity

interface ReviewsController {
    @GET("https://wowowcleaner.com/testAndroidData")
    fun reviews() : Call<List<ReviewEntity>>
}