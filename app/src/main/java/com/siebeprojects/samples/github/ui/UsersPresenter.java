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
import java.util.List;
import java.util.ArrayList;

import com.siebeprojects.samples.github.R;
import com.siebeprojects.samples.github.model.User;

/**
 * UsersPresenter responsible for loading and updating the adapter
 * with new users.
 */
public class UsersPresenter {

    public final static String TAG  = "siu_UsersPresenter";

    /** The users activity */
    private UsersActivity activity;

    /** The users adapter */
    private UsersAdapter adapter;

    /** 
     * Create a new UsersPresenter
     * 
     * @param activity The UsersActivity
     * @param adapter The adapter holding the elements
     */
    UsersPresenter(UsersActivity activity, UsersAdapter adapter) {
        this.activity = activity;
        this.adapter = adapter;
    }

    /** 
     * Load the users from the storage
     */
    public void loadUsers() {

        // first clear all users from the adapter
        adapter.clear();

        User user = new User();
        user.setId(1);
        user.setAvatarUrl("https://avatars.githubusercontent.com/u/1?v=3");
        user.setName("Tom Preston-Werner");
        user.setCreatedAt("2007-10-20T05:24:19Z");

        List<User> users = new ArrayList<User>();
        users.add(user);
        adapter.addItems(users);
    }

    /** 
     * Stop this presenter
     */
    public void stop() {
    }
}
