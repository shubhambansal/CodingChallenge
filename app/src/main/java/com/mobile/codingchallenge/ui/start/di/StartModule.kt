package com.mobile.codingchallenge.ui.start.di

import androidx.lifecycle.ViewModel
import com.mobile.codingchallenge.data.converter.ConfigConverter
import com.mobile.codingchallenge.data.rest.ApiService
import com.mobile.codingchallenge.di.ViewModelKey
import com.mobile.codingchallenge.ui.start.StartPageViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class StartModule {

    @Provides
    @IntoMap
    @ViewModelKey(StartPageViewModel::class)
    fun provideStartPageViewModel(
        apiService: ApiService,
        configConverter: ConfigConverter
    ): ViewModel = StartPageViewModel(
        apiService,
        configConverter
    )
}