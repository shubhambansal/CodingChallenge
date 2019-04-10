package com.mobile.codingchallenge.ui.movies.adapter.paging

import androidx.recyclerview.widget.DiffUtil
import com.mobile.codingchallenge.ui.movies.MovieUiModel

class MovieDiffUtilItemCallback : DiffUtil.ItemCallback<MovieUiModel>() {
    override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem.title == newItem.title
    }
}