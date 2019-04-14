package com.mobile.codingchallenge.di

import com.mobile.codingchallenge.ui.start.FullScreenImageActivity
import com.mobile.codingchallenge.ui.start.StartActivity
import com.mobile.codingchallenge.ui.start.di.StartModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(includes = [StartModule::class])
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [StartModule::class])
    abstract fun bindStartActivity(): StartActivity

    @ContributesAndroidInjector
    abstract fun bindFullScreenImageActivity(): FullScreenImageActivity

}