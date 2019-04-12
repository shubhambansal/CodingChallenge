package com.mobile.codingchallenge.ui.start.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.codingchallenge.ui.start.adapter.viewholder.ImageViewHolder

class ImageAdapter(private var thumbnailList: MutableList<String>) : RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.create(parent)
    }

    override fun getItemCount(): Int = thumbnailList.size


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(thumbnailList[position])
    }

    fun setItem(itemList: List<String>) {

        thumbnailList.clear()
        thumbnailList.addAll(itemList)
        notifyDataSetChanged()
    }

}