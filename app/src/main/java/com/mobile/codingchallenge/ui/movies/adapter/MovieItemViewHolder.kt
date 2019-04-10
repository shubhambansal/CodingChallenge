package com.mobile.codingchallenge.ui.movies.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.codingchallenge.R
import com.mobile.codingchallenge.ui.movies.MovieUiModel

class MovieItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val posterImageView = view.findViewById<ImageView>(R.id.poster_image_view)
    private val movieTitle = view.findViewById<TextView>(R.id.title_text_view)
    private val movieRating = view.findViewById<TextView>(R.id.rating_text_view)
    private val movieReleaseDate = view.findViewById<TextView>(R.id.release_date_text_view)

    fun bind(item: MovieUiModel?) {
        movieTitle.text = item?.title
        movieRating.text = item?.rating
        movieReleaseDate.text = item?.releaseDate

        if (item?.imageUrl.isNullOrEmpty().not()) {
            Glide.with(view.context).load(item?.imageUrl).fitCenter().into(posterImageView)
        }
    }
}