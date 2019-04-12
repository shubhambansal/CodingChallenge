package com.mobile.codingchallenge.data.android

import androidx.annotation.StringRes

interface AndroidResourceManager {

    fun getString(@StringRes id: Int): String
}