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

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class User {

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("gravatar_id")
    @Expose
    private String gravatarId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("followers_url")
    @Expose
    private String followersUrl;
    @SerializedName("subscriptions_url")
    @Expose
    private String subscriptionsUrl;
    @SerializedName("organizations_url")
    @Expose
    private String organizationsUrl;
    @SerializedName("repos_url")
    @Expose
    private String reposUrl;
    @SerializedName("received_events_url")
    @Expose
    private String receivedEventsUrl;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("score")
    @Expose
    private double score;
    @SerializedName("following_url")
    @Expose
    private String followingUrl;
    @SerializedName("gists_url")
    @Expose
    private String gistsUrl;
    @SerializedName("starred_url")
    @Expose
    private String starredUrl;
    @SerializedName("events_url")
    @Expose
    private String eventsUrl;
    @SerializedName("site_admin")
    @Expose
    private boolean siteAdmin;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("blog")
    @Expose
    private String blog;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("hireable")
    @Expose
    private boolean hireable;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("public_repos")
    @Expose
    private int publicRepos;
    @SerializedName("public_gists")
    @Expose
    private int publicGists;
    @SerializedName("followers")
    @Expose
    private int followers;
    @SerializedName("following")
    @Expose
    private int following;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    /**
     * 
     * @return
     * The login
     */
    public String getLogin() {
        return login;
    }

    /**
     * 
     * @param login
     * The login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * 
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     * The avatarUrl
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 
     * @param avatarUrl
     * The avatar_url
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 
     * @return
     * The gravatarId
     */
    public String getGravatarId() {
        return gravatarId;
    }

    /**
     * 
     * @param gravatarId
     * The gravatar_id
     */
    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    /**
     * 
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     * The htmlUrl
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * 
     * @param htmlUrl
     * The html_url
     */
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    /**
     * 
     * @return
     * The followersUrl
     */
    public String getFollowersUrl() {
        return followersUrl;
    }

    /**
     * 
     * @param followersUrl
     * The followers_url
     */
    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    /**
     * 
     * @return
     * The subscriptionsUrl
     */
    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    /**
     * 
     * @param subscriptionsUrl
     * The subscriptions_url
     */
    public void setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    /**
     * 
     * @return
     * The organizationsUrl
     */
    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    /**
     * 
     * @param organizationsUrl
     * The organizations_url
     */
    public void setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
    }

    /**
     * 
     * @return
     * The reposUrl
     */
    public String getReposUrl() {
        return reposUrl;
    }

    /**
     * 
     * @param reposUrl
     * The repos_url
     */
    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    /**
     * 
     * @return
     * The receivedEventsUrl
     */
    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    /**
     * 
     * @param receivedEventsUrl
     * The received_events_url
     */
    public void setReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
    }

    /**
     * 
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     * The score
     */
    public double getScore() {
        return score;
    }

    /**
     * 
     * @param score
     * The score
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * 
     * @return
     * The followingUrl
     */
    public String getFollowingUrl() {
        return followingUrl;
    }

    /**
     * 
     * @param followingUrl
     * The following_url
     */
    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    /**
     * 
     * @return
     * The gistsUrl
     */
    public String getGistsUrl() {
        return gistsUrl;
    }

    /**
     * 
     * @param gistsUrl
     * The gists_url
     */
    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    /**
     * 
     * @return
     * The starredUrl
     */
    public String getStarredUrl() {
        return starredUrl;
    }

    /**
     * 
     * @param starredUrl
     * The starred_url
     */
    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    /**
     * 
     * @return
     * The eventsUrl
     */
    public String getEventsUrl() {
        return eventsUrl;
    }

    /**
     * 
     * @param eventsUrl
     * The events_url
     */
    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    /**
     * 
     * @return
     * The siteAdmin
     */
    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    /**
     * 
     * @param siteAdmin
     * The site_admin
     */
    public void setSiteAdmin(boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

    /**
     * 
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     * The company
     */
    public String getCompany() {
        return company;
    }

    /**
     * 
     * @param company
     * The company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 
     * @return
     * The blog
     */
    public String getBlog() {
        return blog;
    }

    /**
     * 
     * @param blog
     * The blog
     */
    public void setBlog(String blog) {
        this.blog = blog;
    }

    /**
     * 
     * @return
     * The location
     */
    public String getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     * The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     * The hireable
     */
    public boolean isHireable() {
        return hireable;
    }

    /**
     * 
     * @param hireable
     * The hireable
     */
    public void setHireable(boolean hireable) {
        this.hireable = hireable;
    }

    /**
     * 
     * @return
     * The bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * 
     * @param bio
     * The bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * 
     * @return
     * The publicRepos
     */
    public int getPublicRepos() {
        return publicRepos;
    }

    /**
     * 
     * @param publicRepos
     * The public_repos
     */
    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }

    /**
     * 
     * @return
     * The publicGists
     */
    public int getPublicGists() {
        return publicGists;
    }

    /**
     * 
     * @param publicGists
     * The public_gists
     */
    public void setPublicGists(int publicGists) {
        this.publicGists = publicGists;
    }

    /**
     * 
     * @return
     * The followers
     */
    public int getFollowers() {
        return followers;
    }

    /**
     * 
     * @param followers
     * The followers
     */
    public void setFollowers(int followers) {
        this.followers = followers;
    }

    /**
     * 
     * @return
     * The following
     */
    public int getFollowing() {
        return following;
    }

    /**
     * 
     * @param following
     * The following
     */
    public void setFollowing(int following) {
        this.following = following;
    }

    /**
     * 
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     * @return
     * The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     * The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
