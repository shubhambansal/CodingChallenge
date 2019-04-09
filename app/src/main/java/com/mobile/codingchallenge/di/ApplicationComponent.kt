package com.mobile.codingchallenge.di

import com.mobile.codingchallenge.MyApplication
import com.mobile.codingchallenge.ui.movies.MoviePageViewModel
import com.mobile.codingchallenge.ui.start.StartActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RetrofitModule::class])
interface ApplicationComponent {

    fun inject(app: MyApplication)

    fun inject(startActivityViewModel: StartActivityViewModel)

    fun inject(moviePageViewModel: MoviePageViewModel)

    @Component.Builder
    interface Builder{

        fun build() : ApplicationComponent

        fun networkModule(retrofitModule: RetrofitModule) : Builder

        fun applicationModule(applicationModule: ApplicationModule) : Builder
    }

}