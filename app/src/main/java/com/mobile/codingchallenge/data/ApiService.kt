package com.mobile.codingchallenge.data

import com.mobile.codingchallenge.data.model.ConfigResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/3/configuration")
    fun getConfig(@Query("api_key") apiKey: String): Single<ConfigResponse>

}