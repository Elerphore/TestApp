package ru.elerphore.testapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.elerphore.testapplication.api.ReviewsController
import ru.elerphore.testapplication.api.dto.ReviewEntity
import ru.elerphore.testapplication.api.dto.dbEntity
import ru.elerphore.testapplication.db.entity.DB
import ru.elerphore.testapplication.db.entity.ReviewDBEntity
import ru.elerphore.testapplication.viewmodel.Config.BASE_URL

class SecondScreenViewModel : ViewModel() {
    fun getCountries() : LiveData<List<ReviewDBEntity>> = DB.database!!.reviewDao().all()

    fun fetchCountries() {
        val gson = Gson()

        val retrofit =
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()

        val api: ReviewsController = retrofit.create(ReviewsController::class.java)

        CoroutineScope(IO).launch {
            with(api.reviews().execute()) {
                when(this.code()) {
                    200 -> body()?.let { DB.database!!.reviewDao().insertAll(it.raitings.values.map(ReviewEntity::dbEntity)) }
                    else -> { }
                }
            }
        }
    }
}

object Config {
    val BASE_URL = "https://wowowcleaner.com/"
}