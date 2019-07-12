/*
 * This file is part of Siebe Projects samples.
 *
 * Siebe Projects samples is free software: you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Siebe Projects samples is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Lesser GNU General Public License for more details.
 *
 * You should have received a copy of the Lesser GNU General Public License
 * along with Siebe Projects samples.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.siebeprojects.samples.github.util;

import android.util.Log;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Continues scroll listener for continues scrolling down and loading new 
 * elements to be added to the recycler view.
 */
public final class ThresholdOnScrollListener extends RecyclerView.OnScrollListener {

    public final static String TAG  = "samples_ThresholdOnScrollListener";

    /** The layout manager */
    private LinearLayoutManager manager;

    /** The listener interested in load more events */
    private ThresholdListener listener;

    /** The threshold */
    private int threshold;

    /** 
     * Construct a new ThresholdOnScrollListener
     * 
     * @param manager  
     * @param listener 
     * @param threshold
     */
    public ThresholdOnScrollListener(LinearLayoutManager manager, ThresholdListener listener, int threshold) {
        this.manager = manager;
        this.listener = listener;
        this.threshold = threshold;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        
        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount   = manager.getItemCount();
        int firstVisibleItem = manager.findFirstVisibleItemPosition();

        if ((totalItemCount - visibleItemCount) <= (firstVisibleItem + threshold)) {
            notifyListener();
        }
    }

    /**
     * Notify the listener that more items should be loaded
     */
    private void notifyListener() {
        if (listener != null) {
            listener.onThresholdReached();
        }
    }
}
