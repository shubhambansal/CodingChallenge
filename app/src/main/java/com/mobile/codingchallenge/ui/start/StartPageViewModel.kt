package com.mobile.codingchallenge.ui.start

import android.accounts.NetworkErrorException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobile.codingchallenge.BuildConfig
import com.mobile.codingchallenge.R
import com.mobile.codingchallenge.data.android.AndroidResourceManager
import com.mobile.codingchallenge.data.converter.VehicleResponseConverter
import com.mobile.codingchallenge.data.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Provider


class StartPageViewModel(
    private val apiService: ApiService,
    private val vehicleResponseConverter: VehicleResponseConverter,
    private val androidResourceManager: AndroidResourceManager
) : ViewModel() {

    private lateinit var subscription: Disposable

    // State to show-hide progress bar
    val progressBarState = MutableLiveData<Boolean>()
    //To display any error message in a snack bar
    val showErrorSnackBar = MutableLiveData<String>()
    //Navigation listener to open other activity
    val navigationListener = MutableLiveData<Boolean>()

    val resultLiveData = MutableLiveData<List<String>>()

    init {
        loadImages()
    }

    private fun loadImages() {

        // Call Config api here
        subscription = apiService.getImages(BuildConfig.VEHICLE_ID)
            .map { vehicleResponseConverter.convert(it) }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                // show progress bar
                progressBarState.value = true
            }
            .subscribe({ result -> onRetrieveConfigSuccess(result) },
                { error -> handleError(error) }
            )
    }

    private fun onRetrieveConfigSuccess(result: VehicleUiModel) {
        progressBarState.value = false
        resultLiveData.value = result.thumbnailList
    }

    private fun handleError(error: Throwable) {
        progressBarState.value = false

        when (error) {
            is NetworkErrorException -> showErrorSnackBar.value =
                    androidResourceManager.getString(R.string.error_no_internet)
            else -> showErrorSnackBar.value = error.message ?: androidResourceManager.getString(R.string.error_unknown)
        }
    }


    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


    /**
     * Factory Implementation to inject view model class
     */
    class Factory(private val provider: Provider<StartPageViewModel>) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return provider.get() as T // Delegate call to provider
        }
    }
}