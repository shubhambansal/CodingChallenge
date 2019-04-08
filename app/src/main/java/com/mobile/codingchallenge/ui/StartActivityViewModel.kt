package com.mobile.codingchallenge.ui

import com.mobile.codingchallenge.BuildConfig
import com.mobile.codingchallenge.data.ApiService
import com.mobile.codingchallenge.data.model.ConfigResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class StartActivityViewModel : BaseViewModel() {

    @Inject
    lateinit var api: ApiService

    private lateinit var subscription: Disposable


    init {
        loadConfiguration()
    }


    private fun loadConfiguration() {

        // Call Config api here
        subscription = api.getConfig(BuildConfig.API_KEY).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {

                // show progress bar
                progressBarState.value = true
            }
            .subscribe({ result ->
                onRetrieveConfigSuccess(result)
            }, { error -> onRetrieveConfigError(error) })
    }


    private fun onRetrieveConfigSuccess(result: ConfigResponse) {

        progressBarState.value = false
    }

    private fun onRetrieveConfigError(error: Throwable) {

        progressBarState.value = false

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}