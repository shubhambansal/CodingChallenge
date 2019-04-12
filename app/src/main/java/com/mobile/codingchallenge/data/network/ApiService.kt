package com.mobile.codingchallenge.data.network

import com.mobile.codingchallenge.data.model.VehicleResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/svc/a/{id}")
    fun getImages(@Path("id") id: Long): Single<VehicleResponse>

}