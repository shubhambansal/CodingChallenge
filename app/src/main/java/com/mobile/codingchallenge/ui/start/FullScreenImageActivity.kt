package com.mobile.codingchallenge.ui.start

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.Fade
import android.transition.TransitionSet
import androidx.core.transition.doOnEnd
import com.mobile.codingchallenge.R
import com.mobile.codingchallenge.ui.base.BaseActivity
import com.mobile.codingchallenge.ui.base.load
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.full_screen_image_activity_layout.*

private const val EXTRA_IMAGE_URL = "extra_image_url"

class FullScreenImageActivity : BaseActivity() {

    private val url by lazy {
        intent.getStringExtra(EXTRA_IMAGE_URL)
    }

    override fun layoutRes(): Int {
        return R.layout.full_screen_image_activity_layout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        initToolBar()

        supportPostponeEnterTransition()

        full_image_view.transitionName = getString(R.string.transition_full_screen)
        full_image_view.load(url, true) {
            supportStartPostponedEnterTransition()
        }
        window.sharedElementEnterTransition = TransitionSet()
            .addTransition(ChangeImageTransform())
            .addTransition(ChangeBounds())
            .apply {
                doOnEnd { full_image_view.load(url) }
            }

        window.enterTransition = Fade().apply {
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
            excludeTarget(R.id.action_bar_container, true)
        }
    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = ""
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        fun createIntent(context: Context, url: String): Intent {

            return Intent(context, FullScreenImageActivity::class.java)
                .putExtra(EXTRA_IMAGE_URL, url)
        }
    }
}
