package com.mobile.codingchallenge.di

import com.mobile.codingchallenge.ui.detail.MovieDetailActivity
import com.mobile.codingchallenge.ui.movies.di.MovieModule
import com.mobile.codingchallenge.ui.movies.MoviesActivity
import com.mobile.codingchallenge.ui.start.StartActivity
import com.mobile.codingchallenge.ui.start.di.StartModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(includes = [StartModule::class, MovieModule::class])
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [StartModule::class])
    abstract fun bindStartActivity(): StartActivity

    @ContributesAndroidInjector(modules = [MovieModule::class])
    abstract fun bindMovieActivity(): MoviesActivity

    @ContributesAndroidInjector(modules = [MovieModule::class])
    abstract fun bindMovieDetailActivity(): MovieDetailActivity
}