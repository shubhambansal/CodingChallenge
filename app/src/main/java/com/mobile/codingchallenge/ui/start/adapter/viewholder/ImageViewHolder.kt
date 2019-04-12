package com.mobile.codingchallenge.ui.start.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.codingchallenge.R
import kotlinx.android.synthetic.main.image_grid_item_layout.view.*

class ImageViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(url: String) {
        Glide.with(view.context).load(url).into(view.thumbnail_image_view)
    }


    companion object {
        fun create(viewGroup: ViewGroup): ImageViewHolder {

            val layout = R.layout.image_grid_item_layout
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(layout, viewGroup, false)
            return ImageViewHolder(view)
        }
    }
}