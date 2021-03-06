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

package com.siebeprojects.samples.github.service;

import java.util.List;

import com.siebeprojects.samples.github.model.User;
import com.siebeprojects.samples.github.model.SearchResult;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * GitHubApiAdapter 
 */
public class GitHubApiAdapter {

    /** The service end point which is github */
    public final static String GITHUB_ENDPOINT = "https://api.github.com";

    /** The query to search for Java developers */
    public final static String QUERY_JAVA_DEVELOPERS = "language:java";

    /** The singleton instance of this GitHub Api Adapter */
    private static GitHubApiAdapter adapter;

    /** The service */
    private GitHubService service;

    /** 
     * Obtain an instance of this GitHubApiAdapter. 
     * Dependency injection would avoid having this as a 
     * singleton in its own class.
     * 
     * @return The GitHubApiAdapter instance
     */
    public final static GitHubApiAdapter getInstance() {
        if (adapter == null) {
            synchronized (GitHubApiAdapter.class) {
                if (adapter == null) {
                    adapter = new GitHubApiAdapter();
                }
            }
        }
        return adapter;
    }

    /** 
     * Construct a new GitHubApiAdapter
     */
    public GitHubApiAdapter() {

        Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GITHUB_ENDPOINT)
            .build();

        service = retrofit.create(GitHubService.class);
    }

    /** 
     * Search for users with the included search query
     * 
     * @param query 
     * @param perPage
     * @param page 
     * 
     * @return The RXJava Observable with the SearchResult 
     */
    public Observable<SearchResult> searchUsers(String query, int perPage, int page) {
        return service.searchUsers(query, perPage, page);
    }

    /** 
     * Get user details for each user and return the users as a list
     * 
     * @param query 
     * @param perPage
     * @param page 
     * 
     * @return The Observable with a list of users
     */
    public Observable<List<User>> getUserDetailsAsList(List<User> users) {

        return Observable.from(users).concatMap(new Func1<User, Observable<User>>() {
                @Override
                public Observable<User> call(User user) {
                    return service.getUser(user.getLogin());
                }
            }).toList();
    }

    /** 
     * Get user details for each user, the users will be emitted 
     * one by one.
     *
     * @param query 
     * @param perPage
     * @param page 
     * 
     * @return The Observable with a list of users  
     */
    public Observable<User> getUserDetails(List<User> users) {

        return Observable.from(users).concatMap(new Func1<User, Observable<User>>() {
                @Override
                public Observable<User> call(User user) {
                    return service.getUser(user.getLogin());
                }
            });
    }

    /** 
     * Get the user given the name
     * 
     * @param login The login name of the user
     * 
     * @return The Observable with the user 
     */
    public Observable<User> getUser(String login) {
        return service.getUser(login);
    }
}
