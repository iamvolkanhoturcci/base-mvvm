package com.volkanhotur.basemvvm.android.ui.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author volkanhotur
 */

public abstract class BaseRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    /**
     * The total number of items in the data set after the last load
     */
    private int mPreviousTotal = 0;
    /**
     * True if we are still waiting for the last set of data to load.
     */
    private boolean mLoading = true;

    @Override
    public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = Objects.requireNonNull(recyclerView.getLayoutManager()).getItemCount();
        int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

        if (mLoading) {
            if (totalItemCount > mPreviousTotal) {
                mLoading = false;
                mPreviousTotal = totalItemCount;
            }
        }
        if (!mLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem)) {
            onLoadMore();
            mLoading = true;
        }
    }

    public abstract void onLoadMore();

    public void reset(){
        mPreviousTotal = 0;
        mLoading = true;
    }
}
