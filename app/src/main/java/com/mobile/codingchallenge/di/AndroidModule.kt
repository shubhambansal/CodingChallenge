package com.mobile.codingchallenge.di

import android.content.Context
import com.mobile.codingchallenge.data.android.AndroidResourceManager
import com.mobile.codingchallenge.data.android.AndroidResourceManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This module only provides android specific dependencies such as shared preference.
 */
@Module
class AndroidModule {

    @Provides
    @Singleton
    fun provideAndroidResourceManager(context: Context): AndroidResourceManager {
        return AndroidResourceManagerImpl(context)
    }
}