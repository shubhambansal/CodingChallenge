package com.mobile.codingchallenge.ui.start

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.mobile.codingchallenge.R
import com.mobile.codingchallenge.ui.base.BaseActivity
import dagger.android.AndroidInjection

import kotlinx.android.synthetic.main.start_activity_layout.*
import javax.inject.Inject
import androidx.recyclerview.widget.GridLayoutManager
import com.mobile.codingchallenge.ui.base.GridItemDecoration
import com.mobile.codingchallenge.ui.start.adapter.ImageAdapter


class StartActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun layoutRes(): Int {
        return R.layout.start_activity_layout
    }

    private lateinit var viewModel: StartPageViewModel
    private lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        initView()

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(StartPageViewModel::class.java)
        initObservers()
    }


    private fun initView() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.addItemDecoration(GridItemDecoration(10, 2))
        adapter = ImageAdapter(mutableListOf())
        recyclerView.adapter = adapter
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

        })

        viewModel.resultLiveData.observe(this, Observer {
            adapter.setItem(it)
        })

    }

    private fun showStickySnackBarError(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_INDEFINITE).show()
    }

}
