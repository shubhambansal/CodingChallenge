package com.mobile.codingchallenge.ui.base

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Use this class for onClickListener on Recycler view
 */
open class RecyclerItemClickListener(
    context: Context,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.OnItemTouchListener {

    private var gestureDetector: GestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return true
        }

        override fun onLongPress(e: MotionEvent) {
        }
    })


    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {

        var childView = view!!.findChildViewUnder(e!!.x, e.y)
        if (childView != null && gestureDetector.onTouchEvent(e)) {
            onItemClickListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}


    interface OnItemClickListener {

        fun onItemClick(view: View, position: Int)
    }
}
