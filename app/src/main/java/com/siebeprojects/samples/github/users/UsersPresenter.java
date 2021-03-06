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
class UsersPresenter {

    private final static String TAG      = "samples_UsersPresenter";

    private final static int PER_PAGE    = 10;

    /** The current page this is loaded */
    private int curPage;

    /** The page that can be loaded next */
    private int nextPage;

    /** Is the presenter currently loading a page */
    private boolean loading;

    /** Should details be loaded for each item */
    private boolean loadDetails;

    /** The users presenter view */
    private UsersView view;

    /** 
     * Create a new UsersPresenter
     */
    UsersPresenter() {
        this.nextPage = 1;

        // REMIND: enable this if details loading should be activated
        // This will cause rate limit issues with Github.
        //this.loadDetails = true;
    }

    /** 
     * Initialize the presenter with the presenter view
     * 
     * @param view The view that is using this presenter
     */
    void initialize(UsersView view) {
        this.view = view;
    }

    /** 
     * Load the users from the storage
     */
    void loadFirstPage() {

        if (loading || curPage != 0) {
            return;
        }
        loadPage(nextPage);
    }

    /**
     * Load the users from the storage
     */
    void loadNextPage() {

        if (loading || curPage == nextPage) {
            return;
        }
        loadPage(nextPage);
    }

    /** 
     * Set loading and inform the view to show the loading
     * animation.
     */
    private void setLoading(boolean loading) {

        this.loading = loading;
        view.showLoading(loading && view.getItemCount() == 0);
    }

    /** 
     * Load the page with the given number
     * 
     * @param page The page to be loaded
     */
    private void loadPage(int page) {

        setLoading(true);

        GitHubApiAdapter adapter = GitHubApiAdapter.getInstance();
        Observable<SearchResult> result = adapter.searchUsers(GitHubApiAdapter.QUERY_JAVA_DEVELOPERS, PER_PAGE, page);

        result.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<SearchResult>() {

                    @Override
                    public void onCompleted() {
                        if (!loadDetails) {
                            setLoading(false);
                        }
                    }
                    
                    @Override
                        public void onError(Throwable e) {
                        handleRequestError(e);
                    }

                    @Override
                        public void onNext(SearchResult result) {
                        handleSearchResult(result);
                    }
                    });
    }

    /** 
     * Handle the network error situation
     * 
     * @param e The Throwable causing the error
     */
    private void handleRequestError(Throwable e) {
        setLoading(false);
        view.showRequestError();
    }

    /** 
     * Handle the search result, load all user details for 
     * the search result if the loadDetails boolean is true.
     * 
     * @param searchResult The search result with users
     */
    private void handleSearchResult(SearchResult searchResult) {

        if (view.isActive()) {

            List<User> users = searchResult.getItems();
            int totalCount = searchResult.getTotalCount();

            if (loadDetails) {
                loadUserDetails(totalCount, users);
            } else {
                view.addItems(users);
                updatePagination(totalCount, view.getItemCount());
            }
        }
    }

    /** 
     * Handle the incoming user details
     * 
     * @param totalCount    The total amount of items in the search result
     * @param users         The list of users with details
     */
    private void handleUserDetails(int totalCount, List<User> users) {

        if (view.isActive()) {
            view.addItems(users);
            updatePagination(totalCount, view.getItemCount());
        }
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
     * @param totalCount    The total count of items in the search result
     * @param itemCount     The current count of items in the adapter
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
     * @param totalCount
     * @param users The list of users for which user details should be loaded.
     */
    private void loadUserDetails(final int totalCount, List<User> users) {

        GitHubApiAdapter adapter = GitHubApiAdapter.getInstance();
        Observable<List<User>> result = adapter.getUserDetailsAsList(users);

        result.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<List<User>>() {

                    @Override
                    public void onCompleted() {
                        setLoading(false);
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                        handleRequestError(e);
                    }

                    @Override
                    public void onNext(List<User> users) {
                        handleUserDetails(totalCount, users);
                    }
                });
    }
}
