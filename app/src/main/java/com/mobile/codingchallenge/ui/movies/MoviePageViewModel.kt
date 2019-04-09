package com.mobile.codingchallenge.ui.movies

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mobile.codingchallenge.ui.BaseViewModel
import javax.inject.Inject

class MoviePageViewModel : BaseViewModel() {

    @Inject
    lateinit var moviesDataSourceFactory: MoviesDataSourceFactory

    val movies: LiveData<PagedList<MovieUiModel>>

    init {

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .build()

        movies = LivePagedListBuilder(moviesDataSourceFactory, config).build()
    }


    override fun onCleared() {
        super.onCleared()
        moviesDataSourceFactory.movieDataSource.clear()
    }
}