package com.mobile.codingchallenge.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mobile.codingchallenge.R
import com.mobile.codingchallenge.ui.movies.MovieUiModel

class MoviesPagedAdapter(
    diffCallback: DiffUtil.ItemCallback<MovieUiModel>
) : PagedListAdapter<MovieUiModel, MovieItemViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_row_item, parent, false)
        return MovieItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}