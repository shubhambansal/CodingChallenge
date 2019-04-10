package com.mobile.codingchallenge.ui.movies.adapter.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mobile.codingchallenge.ui.movies.MovieUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesDataSourceFactory @Inject constructor(
    val movieDataSource: MovieDataSource
) : DataSource.Factory<Integer, MovieUiModel>() {


    private val movieLiveData = MutableLiveData<MovieDataSource>()


    override fun create(): DataSource<Integer, MovieUiModel> {
        movieLiveData.postValue(movieDataSource)
        return movieDataSource
    }
}