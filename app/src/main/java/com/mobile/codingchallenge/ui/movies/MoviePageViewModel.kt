package com.mobile.codingchallenge.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mobile.codingchallenge.ui.movies.adapter.paging.MoviesDataSourceFactory
import javax.inject.Provider

class MoviePageViewModel(val moviesDataSourceFactory: MoviesDataSourceFactory) :
    ViewModel() {

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

    /**
     * Factory Implementation to inject view model class
     */
    class Factory(val provider: Provider<MoviePageViewModel>) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return provider.get() as T // Delegate call to provider
        }
    }
}