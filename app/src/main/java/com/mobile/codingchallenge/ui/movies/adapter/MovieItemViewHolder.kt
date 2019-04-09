package com.mobile.codingchallenge.ui.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.codingchallenge.R
import com.mobile.codingchallenge.ui.movies.MovieUiModel
import kotlinx.android.synthetic.main.movie_list_row_item.view.*

class MovieItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val posterImageView = view.findViewById<ImageView>(R.id.poster_image_view)
    private val movieTitle = view.findViewById<TextView>(R.id.title_text_view)

    fun bind(item: MovieUiModel?) {
        movieTitle.text = item?.title
    }
}