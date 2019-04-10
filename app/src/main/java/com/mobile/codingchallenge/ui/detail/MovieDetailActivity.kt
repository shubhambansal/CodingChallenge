package com.mobile.codingchallenge.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.mobile.codingchallenge.R
import com.mobile.codingchallenge.ui.BaseActivity
import com.mobile.codingchallenge.ui.movies.MovieUiModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.movie_details_activity_layout.*

class MovieDetailActivity : BaseActivity() {

    private lateinit var model: MovieUiModel

    override fun layoutRes(): Int {
        return R.layout.movie_details_activity_layout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        model = intent.extras.getParcelable(KEY_EXTRA_MOVIE)

        initToolBar()
        initView()
    }

    private fun initToolBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = model.title
    }

    private fun initView() {

        overview_text_view.text = model.overview
        rating_text_view.text = model.rating
        model.imageUrl?.let {
            Glide.with(this).load(model.imageUrl).into(poster_image_view)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    companion object {

        private const val KEY_EXTRA_MOVIE = "key_extra_movie"

        fun createIntent(context: Context, movieUiModel: MovieUiModel): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(KEY_EXTRA_MOVIE, movieUiModel)
            return intent
        }
    }
}