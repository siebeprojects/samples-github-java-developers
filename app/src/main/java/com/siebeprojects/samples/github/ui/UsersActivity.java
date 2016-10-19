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

import android.app.Activity;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ListView;

import com.siebeprojects.samples.github.R;
import com.siebeprojects.samples.github.model.User;

import java.util.List;

/**
 * The main users activity showing a list of 
 * Github users that use Java.
 */
public final class UsersActivity extends AppCompatActivity implements UsersAdapter.OnItemClickListener {

    /** Tag for logging */
    private final static String TAG = "sample_UsersActivity";

    /** The users adapter */
    private UsersAdapter adapter;
        
    /** The presenter loading users */
    private UsersPresenter presenter;

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

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPause() {
        super.onPause();
        adapter.setListener(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onResume() {
        super.onResume();
        adapter.setListener(this);
        presenter.loadUsers();
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
     * Show the user detail activity
     */
    private void showUserActivity() {
    }
}
