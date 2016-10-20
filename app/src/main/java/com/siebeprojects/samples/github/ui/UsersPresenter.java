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

    public final static String TAG      = "samples_UsersPresenter";

    public final static int PER_PAGE    = 10;

    /** The users activity */
    private UsersActivity activity;

    /** The users adapter */
    private UsersAdapter adapter;

    /** The current page this is loaded */
    private int curPage;

    /** The page that can be loaded next */
    private int nextPage;

    /** Is the presenter currently loading a page */
    private boolean loading;
    
    /** 
     * Create a new UsersPresenter
     * 
     * @param activity The UsersActivity
     * @param adapter The adapter holding the elements
     */
    UsersPresenter(UsersActivity activity, UsersAdapter adapter) {
        this.activity = activity;
        this.adapter  = adapter;
        this.nextPage = 1;
    }

    /** 
     * Load the users from the storage
     */
    public void loadFirstPage() {

        if (loading || curPage != 0) {
            return;
        }
        loadPage(nextPage);
    }

    /**
     * Load the users from the storage
     */
    public void loadNextPage() {

        if (loading || curPage == nextPage) {
            return;
        }
        loadPage(nextPage);
    }

    /** 
     * Load the page with the given number
     * 
     * @param page The page to be loaded
     */
    private void loadPage(int page) {

        // Currently loading a page
        loading = true;

        GitHubApiAdapter adapter = GitHubApiAdapter.getInstance();
        Observable<SearchResult> result = adapter.searchUsers(GitHubApiAdapter.QUERY_JAVA_DEVELOPERS, PER_PAGE, page);

        result.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<SearchResult>() {

                    @Override
                    public void onCompleted() {
                        loading = false;
                    }
                    
                    @Override
                        public void onError(Throwable e) {
                        loading = false;
                        Log.i(TAG, "onError: " + e);
                    }

                    @Override
                        public void onNext(SearchResult result) {
                        handleSearchResult(result);
                    }
                    });
    }

    /** 
     * Handle the search result, load all user details for 
     * the search result. For now, the loading of details has been disabled as this
     * causes quickly a rate limit exceeded on github. 
     * 
     * @param searchResult The search result with users
     */
    private void handleSearchResult(SearchResult searchResult) {

        // This could be replaced by the lifecycle management of RXAndroid
        if (activity.isPaused()) {
            return;
        }
        List<User> users = searchResult.getItems();
        addUsersToList(users);

        updatePagination(searchResult.getTotalCount(), adapter.getItemCount());
    }

    /** 
     * Update pagination data and see if more items can be loaded from GitHub.
     * This should be done by the content in the Link header which contains the correct
     * pagination details. For now it is calculated by the totalCount and currently loaded
     * itemCount. 
     *
     * In order to get the Link header info the Observable should contain the Response
     * Object and not directly the SearchResult object.
     * 
     * @param totalCount
     * @param itemCount 
     */
    private void updatePagination(int totalCount, int itemCount) {

        this.curPage = nextPage;
        if (itemCount < totalCount) {
            this.nextPage++;
        } 
    }

    /** 
     * Load the user details for each user object. The rate limit of GitHub does not allow
     * to load user details often and thus has been disabled.
     * 
     * @param users The list of users for which user details should be loaded.
     */
    private void loadUserDetails(List<User> users) {

        GitHubApiAdapter adapter = GitHubApiAdapter.getInstance();
        Observable<List<User>> result = adapter.getUserDetailsAsList(users);

        result.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<List<User>>() {

                    @Override
                    public void onCompleted() {
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e);
                    }

                    @Override
                    public void onNext(List<User> users) {
                        addUsersToList(users);
                    }
                });
    }

    /** 
     * Handle the detail result
     */
    private void addUsersToList(List<User> users) {
        adapter.addItems(users);
    }

    /** 
     * Stop this presenter
     */
    public void stop() {
    }
}
