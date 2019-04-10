package com.mobile.codingchallenge.di

import android.content.Context
import com.mobile.codingchallenge.data.AndroidResourceManager
import dagger.Module
import dagger.Provides
import glotto.mobile.cashbook.data.android.AndroidResourceManagerImpl
import javax.inject.Singleton

/**
 * This module only provides android specific dependencies such as shared preference.
 */
@Module
open class AndroidModule {

    @Provides
    @Singleton
    fun provideAndroidResourceManager(context: Context): AndroidResourceManager {
        return AndroidResourceManagerImpl(context)
    }
}