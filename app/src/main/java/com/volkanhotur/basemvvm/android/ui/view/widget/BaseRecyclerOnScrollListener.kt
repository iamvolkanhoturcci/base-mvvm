package com.volkanhotur.basemvvm.android.ui.view.widget

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * @author volkanhotur
 */
abstract class BaseRecyclerOnScrollListener : RecyclerView.OnScrollListener() {
    /**
     * The total number of items in the data set after the last load
     */
    private var mPreviousTotal = 0
    /**
     * True if we are still waiting for the last set of data to load.
     */
    private var mLoading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView.childCount

        val totalItemCount = recyclerView.layoutManager?.itemCount

        val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager?)?.findFirstVisibleItemPosition()

        if (mLoading) {
            if (totalItemCount ?: 0 > mPreviousTotal) {
                mLoading = false
                mPreviousTotal = totalItemCount ?: 0
            }
        }

        if (!mLoading && totalItemCount ?: 0 - visibleItemCount <= firstVisibleItem ?: 0) {
            onLoadMore()
            mLoading = true
        }
    }

    abstract fun onLoadMore()

    fun reset() {
        mPreviousTotal = 0
        mLoading = true
    }
}