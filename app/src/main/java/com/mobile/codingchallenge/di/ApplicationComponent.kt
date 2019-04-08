package com.mobile.codingchallenge.di

import com.mobile.codingchallenge.MyApplication
import com.mobile.codingchallenge.ui.StartActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RetrofitModule::class])
interface ApplicationComponent {

    fun inject(app: MyApplication)

    fun inject(startActivityViewModel: StartActivityViewModel)

    @Component.Builder
    interface Builder{

        fun build() : ApplicationComponent

        fun networkModule(retrofitModule: RetrofitModule) : Builder

        fun applicationModule(applicationModule: ApplicationModule) : Builder
    }

}