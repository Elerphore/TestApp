package ru.elerphore.testapplication.api

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.elerphore.testapplication.api.dto.ReviewEntity
import ru.elerphore.testapplication.api.dto.dbEntity
import ru.elerphore.testapplication.db.entity.DB
import ru.elerphore.testapplication.viewmodel.Config

object ApiSingleton {

    var api: ReviewsController

    init {
        val gson = Gson()

        val retrofit =
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Config.BASE_URL)
                .build()

        api = retrofit.create(ReviewsController::class.java)
    }

    fun reviews() =
        CoroutineScope(Dispatchers.IO).launch {
            with(api.reviews().execute()) {
                when(this.code()) {
                    200 -> body()?.let {
                        DB.database!!.reviewDao().deleteAll()
                        DB.database!!.reviewDao().insertAll(it.raitings.values.map(ReviewEntity::dbEntity))
                    }
                    else -> { }
                }
            }
        }

}