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
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.siebeprojects.samples.github.R;
import com.siebeprojects.samples.github.model.User;
import com.siebeprojects.samples.github.util.AppUtils;
import com.siebeprojects.samples.github.util.CropCircleTransformation;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;

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

        if (savedInstanceState != null && savedInstanceState.containsKey(EXTRA_LOGIN)) {
            this.login = savedInstanceState.getString(EXTRA_LOGIN);
            this.avatarUrl = savedInstanceState.getString(EXTRA_AVATAR_URL);
        } else {
            Intent intent = getIntent();
            this.login = intent.getStringExtra(EXTRA_LOGIN);            
            this.avatarUrl = intent.getStringExtra(EXTRA_AVATAR_URL);
        }

        initToolbar(login);
        setAvatar(avatarUrl);

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
     * {@inheritDoc}
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString(EXTRA_LOGIN, this.login);
        savedInstanceState.putString(EXTRA_AVATAR_URL, this.avatarUrl);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean val = false;
        switch (item.getItemId()) {
        case android.R.id.home:
            supportFinishAfterTransition();
            val = true;
            break;
        default:
            val = super.onOptionsItemSelected(item);
        }
        return val;
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
     * Set the user into the ui elements
     * 
     * @param user The user to update
     */
    void setUser(User user) {

        TextView tv = (TextView)findViewById(R.id.text_name);
        tv.setText(user.getName());

        tv = (TextView)findViewById(R.id.text_email);
        tv.setText(user.getEmail());

        tv = (TextView)findViewById(R.id.text_followers);
        tv.setText(Integer.toString(user.getFollowers()));

        tv = (TextView)findViewById(R.id.text_createdat);
        tv.setText(AppUtils.getSimpleDateString(this, user.getCreatedAt()));
    }

    /** 
     * Show a request error to the user
     */
    void showRequestError() {
        
        CoordinatorLayout layout = (CoordinatorLayout)findViewById(R.id.layout_coordinator);
        Snackbar snackbar = Snackbar.make(layout, R.string.error_request, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    /** 
     * Set the avatar
     * 
     * @param avatarUrl 
     */
    @SuppressWarnings("unchecked")
    private void setAvatar(String avatarUrl) {

        ImageView bkgImage    = (ImageView)findViewById(R.id.image_background);
        ImageView avatarImage = (ImageView)findViewById(R.id.image_avatar);

        if (TextUtils.isEmpty(avatarUrl)) {
            Glide.clear(bkgImage);
            Glide.clear(avatarImage);
        } else {
            Glide.with(this).load(avatarUrl)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(avatarImage);

            Glide.with(this).load(avatarUrl)
                .bitmapTransform(new BlurTransformation(this, 50), new BrightnessFilterTransformation(this, -0.2f))
                .into(bkgImage);
        }
    }

    /** 
     * 
     * 
     */
    private void initToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(login);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
