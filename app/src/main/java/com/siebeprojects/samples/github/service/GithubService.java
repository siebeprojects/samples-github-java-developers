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

import com.siebeprojects.samples.github.model.User;
import com.siebeprojects.samples.github.model.SearchResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Path;

import rx.Observable;

/**
 * The GitHub Service defining the calls to the GitHub API
 */
public interface GitHubService {

    @GET("/search/users")
    Observable<SearchResult> searchUsers(@Query("q") String query, @Query("per_page") int perPage, @Query("page") int page);

    @GET("users/{login}")
    Observable<User> getUser(@Path("login") String login);
}
