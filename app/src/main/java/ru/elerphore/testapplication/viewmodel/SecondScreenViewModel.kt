package ru.elerphore.testapplication.viewmodel

import android.content.Context
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.elerphore.testapplication.adapter.ReviewRecyclerAdapter
import ru.elerphore.testapplication.api.ApiSingleton
import ru.elerphore.testapplication.db.entity.DB
import ru.elerphore.testapplication.db.entity.ReviewDBEntity
import ru.elerphore.testapplication.db.entity.dtoEntity
import ru.elerphore.testapplication.extension.toPercentage

class SecondScreenViewModel : ViewModel() {

    private val currentLoadingState: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(0)
    }

    private fun getReviews() : LiveData<List<ReviewDBEntity>> = DB.database!!.reviewDao().all()

    fun updateProgressBar(owner: LifecycleOwner, progressBar: ProgressBar, textView: TextView) =
        currentLoadingState.observe(owner) { progress ->
            getReviews().observe(owner) { list ->
                progressBar.progress = (progress.toDouble() / list.size.toDouble() * 100.0).toInt()
                textView.text = (progress.toDouble() / list.size.toDouble() * 100.0).toInt().toPercentage()
            }
        }

    fun loadRecycleView(context: Context, owner: LifecycleOwner, reviewsRecycleView: RecyclerView) = getReviews().observe(owner) {
        it.map(ReviewDBEntity::dtoEntity).also {

            it.forEach { entity ->
                Glide.with(context).load(entity.image)
                currentLoadingState.value = currentLoadingState.value?.plus(1)
            }

            with(ReviewRecyclerAdapter(this, context, it)) {
                reviewsRecycleView.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
                reviewsRecycleView.adapter = this
                reviewsRecycleView.setHasFixedSize(true)
            }
        }
    }

    fun fetchReviews() {
        ApiSingleton.reviews()
    }

}

object Config {
    val BASE_URL = "https://wowowcleaner.com/"
}