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

import com.siebeprojects.samples.github.model.SearchResult;
import com.siebeprojects.samples.github.model.User;
import com.siebeprojects.samples.github.service.GitHubApiAdapter;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * UsersPresenter responsible for loading and updating the adapter
 * with new users.
 */
public class UsersPresenter {

    public final static String TAG  = "samples_UsersPresenter";

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

        GitHubApiAdapter adapter = GitHubApiAdapter.getInstance();
        
        Observable<SearchResult> result = adapter.searchUsers(GitHubApiAdapter.QUERY_JAVA_DEVELOPERS, 10, 1);

        // retrofit + rxjava 
        // Observable should already have a thread attached to it.
        // Compositive subscription to close all at the same time.

        // Retrofit does the 

        result.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<SearchResult>() {

                    @Override
                        public void onCompleted() {
                    }
                    
                    @Override
                        public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e);
                    }

                    @Override
                        public void onNext(SearchResult result) {
                        handleSearchResult(result);
                    }
                    });
    }

    /** 
     * Handle the search result
     * 
     * @param result The search result
     */
    private void handleSearchResult(SearchResult result) {
        
        adapter.clear();
        List<User> users = result.getItems();
        adapter.addItems(users);
    }

    /** 
     * Stop this presenter
     */
    public void stop() {
    }
}
