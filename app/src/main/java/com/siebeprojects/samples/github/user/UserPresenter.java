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

import android.util.Log;

import com.siebeprojects.samples.github.model.User;
import com.siebeprojects.samples.github.service.GitHubApiAdapter;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * UserPresenter responsible for loading the details of the given user.
 */
public class UserPresenter {

    public final static String TAG      = "samples_UserPresenter";

    /** The user activity */
    private UserActivity activity;

    /** Is the presenter currently loading a user */
    private boolean loading;

    /** 
     * Create a new UserPresenter
     * 
     * @param activity The UsersActivity
     * @param adapter The adapter holding the elements
     */
    UserPresenter(UserActivity activity) {
        this.activity = activity;
    }

    /** 
     * Load the users from the storage
     */
    public void loadUser(String userName) {

        if (loading) {
            return;
        }
        setLoading(true);

        GitHubApiAdapter adapter = GitHubApiAdapter.getInstance();
        Observable<User> result = adapter.getUser(userName);

        result.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<User>() {

                    @Override
                    public void onCompleted() {
                        setLoading(false);
                    }
                    
                    @Override
                        public void onError(Throwable e) {
                        handleRequestError(e);
                    }

                    @Override
                        public void onNext(User user) {
                        handleUserResult(user);
                    }
                    });
    }

    /** 
     * Set the loading state
     *
     * @param loading true when loading, false otherwise
     */
    private void setLoading(boolean loading) {
        this.loading = loading;
    }

    /** 
     * Handle the network error situation
     * 
     * @param e The Throwable causing the error
     */
    private void handleRequestError(Throwable e) {

        setLoading(false);
        if (!activity.isPaused()) {
            activity.showRequestError();
        }
    }

    /** 
     * Handle the loaded user
     * 
     * @param user 
     */
    private void handleUserResult(User user) {
        if (!activity.isPaused()) {
            activity.setUser(user);
        }
    }
}
