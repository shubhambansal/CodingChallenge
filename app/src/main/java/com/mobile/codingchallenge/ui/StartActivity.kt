package com.mobile.codingchallenge.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mobile.codingchallenge.R

import kotlinx.android.synthetic.main.start_activity_layout.*

class StartActivity : AppCompatActivity() {

    private lateinit var viewModel: StartActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity_layout)

        viewModel = ViewModelProviders.of(this).get(StartActivityViewModel::class.java)
        initObservers()
    }


    private fun initObservers() {

        viewModel.progressBarState.observe(this, Observer { value ->
            progress_bar.visibility = if (value) View.VISIBLE else View.GONE
        })
    }

}
