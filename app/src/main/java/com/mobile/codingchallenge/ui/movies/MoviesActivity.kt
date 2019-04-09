package com.mobile.codingchallenge.ui.movies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.codingchallenge.R
import com.mobile.codingchallenge.ui.movies.adapter.MovieDiffUtilItemCallback
import com.mobile.codingchallenge.ui.movies.adapter.MoviesPagedAdapter
import kotlinx.android.synthetic.main.movie_activity_layout.*

/**
 * Shows the list of latest movies in the list.
 * The user can scroll the list and the new movies will be automatically loaded
 */
class MoviesActivity : AppCompatActivity() {

    lateinit var viewModel: MoviePageViewModel
    lateinit var adapter: MoviesPagedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_activity_layout)

        initView()

        viewModel = ViewModelProviders.of(this).get(MoviePageViewModel::class.java)
        initObservers()
    }

    private fun initView() {

        movie_list_recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = MoviesPagedAdapter(MovieDiffUtilItemCallback())
        movie_list_recycler_view.adapter = adapter
    }

    private fun initObservers() {
        viewModel.movies.observe(this, Observer { adapter.submitList(it) })
    }

    companion object {
        fun createIntent(context: Context): Intent {
            val intent = Intent(context, MoviesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            return intent
        }
    }
}
