package com.mobile.codingchallenge.ui.movies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import com.mobile.codingchallenge.R

/**
 * Shows the list of latest movies in the list.
 * The user can scroll the list and the new movies will be automatically loaded
 */
class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_activity_layout)
        initObservers()
    }

    private fun initObservers() {

    }

    companion object {
        fun createIntent(context: Context): Intent {
            val intent = Intent(context, MoviesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            return intent
        }
    }
}
