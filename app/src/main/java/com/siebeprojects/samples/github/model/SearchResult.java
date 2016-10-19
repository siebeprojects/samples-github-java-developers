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

package com.siebeprojects.samples.github.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The SearchResult Pojo storing user search results
 */
public class SearchResult {

    @SerializedName("total_count")
    @Expose
    private int totalCount;
    @SerializedName("incomplete_results")
    @Expose
    private boolean incompleteResults;
    @SerializedName("items")
    @Expose
    private List<User> items = new ArrayList<User>();

    /**
     * 
     * @return
     * The totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 
     * @param totalCount
     * The total_count
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 
     * @return
     * The incompleteResults
     */
    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    /**
     * 
     * @param incompleteResults
     * The incomplete_results
     */
    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    /**
     * 
     * @return
     * The items
     */
    public List<User> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     * The items
     */
    public void setItems(List<User> items) {
        this.items = items;
    }

}
