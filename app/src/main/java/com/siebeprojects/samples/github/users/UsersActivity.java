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

package com.siebeprojects.samples.github.users;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.siebeprojects.samples.github.R;
import com.siebeprojects.samples.github.model.User;
import com.siebeprojects.samples.github.user.UserActivity;
import com.siebeprojects.samples.github.util.ThresholdListener;
import com.siebeprojects.samples.github.util.ThresholdOnScrollListener;

/**
 * The main users activity showing a list of 
 * Github users that use Java.
 */
public final class UsersActivity extends AppCompatActivity implements UsersAdapter.OnItemClickListener, ThresholdListener {

    /** Key for animating the avatar profile */
    private final static String KEY_PROFILE      = "profile";

    /** The scrolling threshold before new items should be loaded */
    private final static int SCROLL_THRESHOLD   = 5;

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

        // construct the presenter
        presenter = new UsersPresenter();
        presenter.setActivity(this);
        presenter.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        adapter.setListener(this);

        ThresholdOnScrollListener listener = new ThresholdOnScrollListener(manager, this, SCROLL_THRESHOLD);
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
    public void onItemClick(User user, int position, View transView) {
        Intent intent = UserActivity.createLaunchIntent(this, user);
        
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, transView, KEY_PROFILE);
        startActivity(intent, options.toBundle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onThresholdReached() {
        presenter.loadNextPage();
    }

    /** 
     * Show the loading animation or not
     */
    void showLoading(boolean show) {
        View v = findViewById(R.id.progressbar);
        v.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    /** 
     * Is the activity paused
     * 
     * @return true when paused, false otherwise 
     */
    boolean isPaused() {
        return paused;
    }

    /** 
     * Show a request error to the user
     */
    void showRequestError() {
        View view = findViewById(R.id.layout_activity);
        Snackbar snackbar = Snackbar.make(view, R.string.error_request, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}
