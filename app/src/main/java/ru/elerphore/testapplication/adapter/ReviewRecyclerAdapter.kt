package ru.elerphore.testapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.elerphore.testapplication.R
import ru.elerphore.testapplication.api.dto.ReviewEntity

class ReviewRecyclerAdapter(val context: Context, _reviews: List<ReviewEntity>) : RecyclerView.Adapter<ReviewRecyclerAdapter.Holder>() {

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
        holder.title!!.text = reviews[position].title
        Glide.with(context).load(reviews[position].image).apply(RequestOptions().centerCrop()).into(holder.image!!)
    }

    fun setReview(reviews: List<ReviewEntity>) {
        this.reviews = reviews
    }

    override fun getItemCount(): Int = reviews.size
}