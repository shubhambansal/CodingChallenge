package com.mobile.codingchallenge.di

import android.content.Context
import com.mobile.codingchallenge.MyApplication
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class ApplicationModule(private val myApplication: MyApplication) {

    @Provides
    @Reusable
    fun app(): MyApplication {
        return myApplication
    }

    @Provides
    @Reusable
    fun context(): Context {
        return myApplication
    }
}