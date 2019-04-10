package com.mobile.codingchallenge.ui.movies.adapter.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.mobile.codingchallenge.BuildConfig
import com.mobile.codingchallenge.data.converter.MovieResponseConverter
import com.mobile.codingchallenge.data.rest.ApiService
import com.mobile.codingchallenge.data.rest.QueryConstant
import com.mobile.codingchallenge.ui.movies.MovieUiModel
import com.mobile.codingchallenge.ui.util.DateUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDataSource @Inject constructor(
    private val apiService: ApiService,
    private val movieResponseConverter: MovieResponseConverter,
    private val dateUtil: DateUtil
) : ItemKeyedDataSource<Integer, MovieUiModel>() {


    private val compositeDisposable = CompositeDisposable()
    private var pageNumber: Int = 1

    val loadingStateLiveDatta = MutableLiveData<Boolean>()
    val paginationErrorLiveData = MutableLiveData<String>()


    override fun loadInitial(params: LoadInitialParams<Integer>, callback: LoadInitialCallback<MovieUiModel>) {

        Log.d("MovieDataSource", "Fetching first page: $pageNumber")

        val disposable = apiService.getMovies(getDefaultQueryMap(), pageNumber)
            .map { movieResponseConverter.convert(it) }
            .doOnSubscribe { loadingStateLiveDatta.postValue(true) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ resultList -> onShowResult(resultList, callback) }, this::onError)

        compositeDisposable.add(disposable)

    }

    override fun loadAfter(params: LoadParams<Integer>, callback: LoadCallback<MovieUiModel>) {

        Log.d("MovieDataSource", "Fetching next page: $pageNumber")

        val disposable = apiService.getMovies(getDefaultQueryMap(), pageNumber)
            .map { movieResponseConverter.convert(it) }
            .doOnSubscribe { loadingStateLiveDatta.postValue(true) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ resultList -> onMoviesFetched(resultList, callback) }, this::onError)

        compositeDisposable.add(disposable)
    }

    override fun loadBefore(params: LoadParams<Integer>, callback: LoadCallback<MovieUiModel>) {
        // Do Nothing
    }

    override fun getKey(item: MovieUiModel): Integer {
        return Integer(pageNumber)
    }


    private fun onShowResult(
        resultList: List<MovieUiModel>,
        callback: LoadInitialCallback<MovieUiModel>
    ) {
        loadingStateLiveDatta.postValue(false)
        pageNumber++
        callback.onResult(resultList)

    }

    private fun onMoviesFetched(
        resultList: List<MovieUiModel>,
        callback: LoadCallback<MovieUiModel>
    ) {
        loadingStateLiveDatta.postValue(false)
        pageNumber++;
        callback.onResult(resultList)
    }

    private fun onError(throwable: Throwable) {

        loadingStateLiveDatta.postValue(false)
        paginationErrorLiveData.postValue(throwable.message)
    }

    fun clear() {
        compositeDisposable.clear()
        pageNumber = 1
    }


    private fun getDefaultQueryMap(): Map<String, String> {

        val queryMap = mutableMapOf<String, String>()

        queryMap[QueryConstant.API_KEY.key] = BuildConfig.API_KEY
        queryMap[QueryConstant.LANGUAGE.key] = BuildConfig.DEFAULT_LABGUAGE
        queryMap[QueryConstant.SORT_BY.key] = BuildConfig.DEFAULT_SORT
        queryMap[QueryConstant.INCLUDE_ADULT.key] = "false"
        queryMap[QueryConstant.INCLUDE_VIDEO.key] = "false"
        queryMap[QueryConstant.RELEASE_DATE.key] = dateUtil.todayDate()

        return queryMap
    }

}