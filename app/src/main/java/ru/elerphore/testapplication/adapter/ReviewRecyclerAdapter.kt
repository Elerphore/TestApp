package ru.elerphore.testapplication.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import ru.elerphore.testapplication.R
import ru.elerphore.testapplication.api.dto.ReviewEntity
import ru.elerphore.testapplication.viewmodel.SecondScreenViewModel

class ReviewRecyclerAdapter(
    val secondScreenViewModel: SecondScreenViewModel,
    val context: Context,
    _reviews: List<ReviewEntity>
) : RecyclerView.Adapter<ReviewRecyclerAdapter.Holder>() {

    private var reviews: List<ReviewEntity> = _reviews

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.reviewImage)
        val title: TextView = itemView.findViewById(R.id.reviewTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = reviews[position].title
        Glide.with(context)
            .load(reviews[position].image)
            .listener(GlideListener(secondScreenViewModel, reviews.size))
            .apply(RequestOptions().override(100, 100))
            .apply(RequestOptions().centerCrop()).into(holder.image)
    }

    fun setReview(reviews: List<ReviewEntity>) {
        this.reviews = reviews
    }

    override fun getItemCount(): Int = reviews.size

    class GlideListener(private val viewModel: SecondScreenViewModel, private val size: Int) : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            println("test1")
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {

            if(this.viewModel.currentLoadingState.value != null && this.size > this.viewModel.currentLoadingState.value!!) {
                this.viewModel.currentLoadingState.value = this.viewModel.currentLoadingState.value?.plus(1)
            }

            return false
        }

    }
}
