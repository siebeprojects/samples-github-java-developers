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

package com.siebeprojects.samples.github.user;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.siebeprojects.samples.github.R;
import com.siebeprojects.samples.github.model.User;

/**
 * The user details activity.
 */
public final class UserActivity extends AppCompatActivity {

    /** Tag for logging */
    private final static String TAG                = "samples_UserActivity";

    /** Intent and Bundle storage key values */
    public final static String EXTRA_LOGIN         = "login";
    public final static String EXTRA_AVATAR_URL    = "avatarurl";

    /** The presenter loading a user */
    private UserPresenter presenter;

    /** the activity is paused */
    private boolean paused;

    /** The login name of the user */
    private String login;

    /** The avatar url of the user */
    private String avatarUrl;

    /** 
     * Create launch activity
     * 
     * @param context 
     * @param user
     * 
     * @return The launch intent
     */
    public final static Intent createLaunchIntent(Context context, User user) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(EXTRA_LOGIN, user.getLogin());
        intent.putExtra(EXTRA_AVATAR_URL, user.getAvatarUrl());
        return intent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        presenter = new UserPresenter(this);

        // get the conversation id value
        if (savedInstanceState != null && savedInstanceState.containsKey(EXTRA_LOGIN)) {
            this.login = savedInstanceState.getString(EXTRA_LOGIN);
            this.avatarUrl = savedInstanceState.getString(EXTRA_AVATAR_URL);
        } else {
            Intent intent = getIntent();
            this.login = intent.getStringExtra(EXTRA_LOGIN);            
            this.avatarUrl = intent.getStringExtra(EXTRA_AVATAR_URL);
        }
        presenter = new UserPresenter(this);
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
        presenter.loadUser(login);
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
     * Set the user into the ui elements
     * 
     * @param user The user to update
     */
    public void setUser(User user) {
        Log.i(TAG, "setUser: " + user);
    }
}
