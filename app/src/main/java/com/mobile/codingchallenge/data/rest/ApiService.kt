package com.mobile.codingchallenge.data.rest

import com.mobile.codingchallenge.data.model.ConfigResponse
import com.mobile.codingchallenge.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("/3/configuration")
    fun getConfig(@Query("api_key") apiKey: String): Single<ConfigResponse>

    @GET("/3/discover/movie")
    fun getMovies(@QueryMap queryMap: Map<String, String>, @Query("page") page: Int): Single<MovieResponse>

}