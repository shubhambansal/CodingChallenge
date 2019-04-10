package com.mobile.codingchallenge.ui.movies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mobile.codingchallenge.R
import com.mobile.codingchallenge.ui.BaseActivity
import com.mobile.codingchallenge.ui.movies.adapter.paging.MovieDiffUtilItemCallback
import com.mobile.codingchallenge.ui.movies.adapter.MoviesPagedAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.movie_activity_layout.*
import javax.inject.Inject

/**
 * Shows the list of latest movies in the list.
 * The user can scroll the list and the new movies will be automatically loaded
 */
class MoviesActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun layoutRes(): Int {
        return R.layout.movie_activity_layout
    }

    private lateinit var viewModel: MoviePageViewModel
    private lateinit var adapter: MoviesPagedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviePageViewModel::class.java)

        initView()
        initObservers()
    }

    private fun initView() {

        movie_list_recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = MoviesPagedAdapter(MovieDiffUtilItemCallback())
        movie_list_recycler_view.adapter = adapter
    }

    private fun initObservers() {
        viewModel.movies.observe(this, Observer { adapter.submitList(it) })

        viewModel.moviesDataSourceFactory.movieDataSource.loadingStateLiveDatta.observe(this, Observer {
            progress_bar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.moviesDataSourceFactory.movieDataSource.paginationErrorLiveData.observe(this, Observer {
            showSnackBarError(it)
        })
    }

    private fun showSnackBarError(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show()
    }

    companion object {
        fun createIntent(context: Context): Intent {
            val intent = Intent(context, MoviesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            return intent
        }
    }
}
