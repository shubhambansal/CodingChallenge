package com.mobile.codingchallenge.ui

import dagger.android.support.DaggerAppCompatActivity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable


abstract class BaseActivity : DaggerAppCompatActivity() {

    @LayoutRes
    protected abstract fun layoutRes(): Int

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
    }
}