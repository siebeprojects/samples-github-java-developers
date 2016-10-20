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

package com.siebeprojects.samples.github.ui;

import android.util.Log;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.siebeprojects.samples.github.R;
import com.siebeprojects.samples.github.model.User;

/**
 * The main users activity showing a list of 
 * Github users that use Java.
 */
public final class UsersActivity extends AppCompatActivity implements UsersAdapter.OnItemClickListener, ThresholdListener {

    /** Tag for logging */
    private final static String TAG = "samples_UsersActivity";

    /** The users adapter */
    private UsersAdapter adapter;
        
    /** The presenter loading users */
    private UsersPresenter presenter;

    /** the activity is paused */
    private boolean paused;

    /** The ThresholdOnScrollListener */
    private ThresholdOnScrollListener listener;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview_users);

        // initialize the adapter
        adapter = new UsersAdapter(this);
        presenter = new UsersPresenter(this, adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        adapter.setListener(this);

        ThresholdOnScrollListener listener = new ThresholdOnScrollListener(manager, this, 5);
        recyclerView.addOnScrollListener(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPause() {
        super.onPause();
        paused = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onResume() {
        super.onResume();
        paused = false;
        presenter.loadFirstPage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onItemClick(User user, int position) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onThresholdReached() {
        presenter.loadNextPage();
    }

    /** 
     * Is the activity paused
     * 
     * @return true when paused, false otherwise 
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Show the user detail activity
     */
    private void showUserActivity() {
    }
}
