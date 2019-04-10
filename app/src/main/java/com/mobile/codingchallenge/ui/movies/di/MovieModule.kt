package com.mobile.codingchallenge.ui.movies.di

import androidx.lifecycle.ViewModel
import com.mobile.codingchallenge.di.ViewModelKey
import com.mobile.codingchallenge.ui.movies.MoviePageViewModel
import com.mobile.codingchallenge.ui.movies.adapter.paging.MoviesDataSourceFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class MovieModule {

    @Provides
    @IntoMap
    @ViewModelKey(MoviePageViewModel::class)
    fun provideMoviePageViewModel(
        moviesDataSourceFactory: MoviesDataSourceFactory
    ): ViewModel = MoviePageViewModel(moviesDataSourceFactory)
}