package com.mobile.codingchallenge.data

import androidx.annotation.StringRes

interface AndroidResourceManager {

    fun getString(@StringRes id: Int): String
}