package com.mobile.codingchallenge

import android.app.Application
import com.mobile.codingchallenge.di.ApplicationModule
import com.mobile.codingchallenge.di.DaggerApplicationComponent
import com.mobile.codingchallenge.di.RetrofitModule

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }

}