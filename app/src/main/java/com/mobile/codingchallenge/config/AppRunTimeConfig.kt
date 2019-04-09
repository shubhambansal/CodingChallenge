package com.mobile.codingchallenge.config

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRunTimeConfig @Inject constructor(){

    var basePosterUrl : String? = null

    var baseFallbackUrl : String? = null

}