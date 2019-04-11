package com.mobile.codingchallenge.data.model

import com.squareup.moshi.Json

data class VehicleResponse(@field:Json(name = "images") val imageList: List<Images>)

data class Images(val uri: String)
