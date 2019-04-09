package com.mobile.codingchallenge.ui

import android.accounts.NetworkErrorException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.codingchallenge.di.ApplicationComponent
import com.mobile.codingchallenge.di.DaggerApplicationComponent
import com.mobile.codingchallenge.di.RetrofitModule
import com.mobile.codingchallenge.ui.movies.MoviePageViewModel
import com.mobile.codingchallenge.ui.start.StartActivityViewModel

abstract class BaseViewModel : ViewModel() {

    // State to show-hide progress bar
    val progressBarState = MutableLiveData<Boolean>()

    //To display any error message in a snack bar
    val showErrorSnackBar = MutableLiveData<String>()

    internal fun handleError(error: Throwable) {
        progressBarState.value = false

        when (error) {
            //TODO replace hardcoded string
            is NetworkErrorException -> showErrorSnackBar.value = "No Internet Connection!"
            else -> showErrorSnackBar.value = error.message
        }
    }

    private val injector: ApplicationComponent = DaggerApplicationComponent.builder()
        .networkModule(RetrofitModule)
        .build()


    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is StartActivityViewModel -> injector.inject(this)
            is MoviePageViewModel -> injector.inject(this)
        }
    }
}