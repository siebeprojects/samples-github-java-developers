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

package com.siebeprojects.samples.github.server;

import com.google.gson.Gson;
import com.rejasupotaro.octodroid.GitHubClient;
import com.rejasupotaro.octodroid.GsonProvider;
import com.rejasupotaro.octodroid.http.ApiClient;

/**
 * The controller class handling the GitHubClient instance retrieval.
 */
public final class GitHubController {

    /** the private instance of the Github client */
    private GitHubClient client;

    /** 
     * The GitHub controller
     * 
     * @param ApiClient
     * 
     * @return The github client
     */
    public GitHubClient getClient() {

        if (client != null) {
            return client;
        }
        synchronized (GitHubController.class) {
            if (client == null) {
                client = new GitHubClient(new ApiClient());
            }
        }
        return client;
    }
}
