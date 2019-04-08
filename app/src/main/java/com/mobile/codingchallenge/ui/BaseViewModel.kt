package com.mobile.codingchallenge.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.codingchallenge.di.ApplicationComponent
import com.mobile.codingchallenge.di.DaggerApplicationComponent
import com.mobile.codingchallenge.di.RetrofitModule

abstract class BaseViewModel : ViewModel(){

    // State to show-hide progress bar
    val progressBarState = MutableLiveData<Boolean>()

    private val injector: ApplicationComponent = DaggerApplicationComponent.builder()
        .networkModule(RetrofitModule)
        .build()


    init {
        inject()
    }

    private fun inject(){
        when(this){
            is StartActivityViewModel -> injector.inject(this)
        }
    }
}