package com.mobile.codingchallenge.di

import android.app.Application
import com.mobile.codingchallenge.MyApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.support.AndroidSupportInjectionModule


@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityBindingModule::class, NetworkModule::class]
)
interface ApplicationComponent {

    fun inject(app: MyApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): ApplicationComponent
    }
}