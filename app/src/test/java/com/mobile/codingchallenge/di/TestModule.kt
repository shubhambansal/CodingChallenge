package com.mobile.codingchallenge.di

import com.mobile.codingchallenge.config.AppRunTimeConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestModule {

    @Provides
    @Singleton
    fun provideRunTimeAppConfig(): AppRunTimeConfig{
        return AppRunTimeConfig()
    }
}