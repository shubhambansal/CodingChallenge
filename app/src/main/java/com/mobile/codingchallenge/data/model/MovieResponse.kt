package com.mobile.codingchallenge.data.model

import com.squareup.moshi.Json

data class MovieResponse(val page : Int,
                         @field:Json(name = "total_results") val totalResult: Int,
                         @field:Json(name = "total_pages") val totalPages: Int,
                         val results : List<Result>
                         )