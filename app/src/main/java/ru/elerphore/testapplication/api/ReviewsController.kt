package ru.elerphore.testapplication.api

import retrofit2.Call
import retrofit2.http.GET
import ru.elerphore.testapplication.api.dto.ReviewResponseEntity

interface ReviewsController {
    @GET("/testAndroidData")
    fun reviews() : Call<ReviewResponseEntity>

}