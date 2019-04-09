package com.mobile.codingchallenge.ui.start

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.mobile.codingchallenge.R
import com.mobile.codingchallenge.ui.movies.MoviesActivity

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

        // to display progress state
        viewModel.progressBarState.observe(this, Observer { value ->
            progress_bar.visibility = if (value) View.VISIBLE else View.GONE
        })

        // to display error message
        viewModel.showErrorSnackBar.observe(this, Observer { msg ->
            showStickySnackBarError(msg)
        })

        //If config loaded than we'll go to display list of movies
        viewModel.navigationListener.observe(this, Observer {

            if (it)
                startActivity(MoviesActivity.createIntent(this))
            else {
                showStickySnackBarError(getString(R.string.error_config))
            }
        })

    }

    private fun showStickySnackBarError(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_INDEFINITE).show()
    }

}
