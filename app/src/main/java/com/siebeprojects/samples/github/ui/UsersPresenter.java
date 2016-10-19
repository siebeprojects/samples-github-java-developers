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
import com.siebeprojects.samples.github.server.GitHubController;

import com.rejasupotaro.octodroid.models.User;
import com.rejasupotaro.octodroid.GitHubClient;
import com.rejasupotaro.octodroid.GitHubClient;
import com.rejasupotaro.octodroid.http.ApiClient;
import com.rejasupotaro.octodroid.http.Method;
import com.rejasupotaro.octodroid.http.Params;
import com.rejasupotaro.octodroid.http.Response;
import com.rejasupotaro.octodroid.models.Event;
import com.rejasupotaro.octodroid.models.Notification;
import com.rejasupotaro.octodroid.models.Repository;
import com.rejasupotaro.octodroid.models.SearchResult;
import com.rejasupotaro.octodroid.models.User;
import com.rejasupotaro.octodroid.http.Response;
import com.rejasupotaro.octodroid.http.Link;
import com.rejasupotaro.octodroid.http.Params;
import com.rejasupotaro.octodroid.models.User;

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

    /** The github controller */
    private GitHubController controller;

    /** 
     * Create a new UsersPresenter
     * 
     * @param activity The UsersActivity
     * @param adapter The adapter holding the elements
     */
    UsersPresenter(UsersActivity activity, UsersAdapter adapter) {
        this.activity = activity;
        this.adapter = adapter;

        // this may be set through Dependency injection
        this.controller = new GitHubController();
    }

    /** 
     * Load the users from the storage
     */
    public void loadUsers() {

        GitHubClient client = controller.getClient();

        Params param = new Params();
        param.add("per_page", "5");
        param.add("q", "language:java");
        
        Observable<Response<SearchResult<User>>> result = client.searchUsers(param);
        result.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .unsafeSubscribe(new Subscriber<Response<SearchResult<User>>>() {

                    @Override
                        public void onCompleted() {
                    }
                    
                    @Override
                        public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e);
                    }

                    @Override
                        public void onNext(Response<SearchResult<User>> response) {
                        handleSearchResult(response.entity());
                    }
                    });
    }

    /** 
     * Handle the search result
     * 
     * @param result
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
