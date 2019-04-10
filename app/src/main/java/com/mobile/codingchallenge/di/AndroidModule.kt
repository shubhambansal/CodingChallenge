package com.mobile.codingchallenge.di

import android.content.Context
import android.net.Uri
import android.util.Log
import com.mobile.codingchallenge.data.AndroidResourceManager
import dagger.Module
import dagger.Provides
import glotto.mobile.cashbook.data.android.AndroidResourceManagerImpl
import okhttp3.OkHttpClient
import java.lang.Exception
import java.util.concurrent.Executors
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