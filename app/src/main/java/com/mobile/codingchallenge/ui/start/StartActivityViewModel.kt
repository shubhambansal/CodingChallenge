package com.mobile.codingchallenge.ui.start

import androidx.lifecycle.MutableLiveData
import com.mobile.codingchallenge.BuildConfig
import com.mobile.codingchallenge.data.converter.ConfigConverter
import com.mobile.codingchallenge.data.rest.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class StartActivityViewModel : BaseViewModel() {

    @Inject
    lateinit var api: ApiService

    @Inject
    lateinit var configConverter: ConfigConverter

    private lateinit var subscription: Disposable

    val navigationListener = MutableLiveData<Boolean>()


    init {
        loadConfiguration()
    }


    private fun loadConfiguration() {

        // Call Config api here
        subscription = api.getConfig(BuildConfig.API_KEY)
            .map { configConverter.convert(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                // show progress bar
                progressBarState.value = true
            }
            .subscribe({ onRetrieveConfigSuccess() },
                { error -> onRetrieveConfigError(error) }
            )
    }


    private fun onRetrieveConfigSuccess() {
        progressBarState.value = false
        navigationListener.value = true
    }


    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}