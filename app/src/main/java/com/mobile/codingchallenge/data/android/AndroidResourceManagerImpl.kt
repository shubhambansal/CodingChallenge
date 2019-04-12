package com.mobile.codingchallenge.data.android

import android.content.Context

/**
 * Use this class for accessing android system specific resources.
 */
class AndroidResourceManagerImpl constructor(private val context: Context) :
    AndroidResourceManager {

    override fun getString(id: Int): String {
        return context.getString(id)
    }
}