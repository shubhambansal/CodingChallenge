package com.mobile.codingchallenge.ui.movies

import androidx.lifecycle.ViewModel
import com.mobile.codingchallenge.data.converter.ConfigConverter
import com.mobile.codingchallenge.data.rest.ApiService
import com.mobile.codingchallenge.di.ViewModelKey
import com.mobile.codingchallenge.ui.start.StartPageViewModel
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