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

import android.content.Context;

import android.support.v7.widget.RecyclerView;

import android.text.TextUtils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide; 

import com.siebeprojects.samples.github.R;
import com.siebeprojects.samples.github.model.User;

import java.util.ArrayList;
import java.util.List;

import com.siebeprojects.samples.github.util.CropCircleTransformation;
import com.siebeprojects.samples.github.util.AppUtils;

/**
 *
 */
class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private final static String TAG = "samples_UsersAdapter";

    /** The list of users */
    private List<User> items;

    /** The listener */
    private OnItemClickListener listener;
    
    /** The users activity */
    private UsersActivity activity;

    /** 
     * Create a new UsersAdapter
     * 
     * @param activity
     */
    UsersAdapter(UsersActivity activity) {
        this.items = new ArrayList<User>();
        this.activity = activity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.listitem_users, parent, false);
        return new ViewHolder(userView);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(UsersAdapter.ViewHolder holder, int position) {

        User user = items.get(position);
        String name = user.getName();
        String createdAt = user.getCreatedAt();

        // set the name field 
        if (TextUtils.isEmpty(name)) {
            name = user.getLogin();
        }
        holder.name.setText(name);

        // set the description field 
        holder.description.setText(AppUtils.getSimpleDateString(activity, createdAt));
        holder.description.setVisibility(TextUtils.isEmpty(createdAt) ? View.GONE : View.VISIBLE);

        // set the avatar fields with initials
        holder.initials.setText(getInitials(name));

        String url = user.getAvatarUrl();
        if (TextUtils.isEmpty(url)) {
            Glide.clear(holder.avatar);
        } else {
            Glide.with(activity).load(url)
                .bitmapTransform(new CropCircleTransformation(activity))
                .into(holder.avatar);
        }
    }

    /** 
     * Set the listener in this adapter
     * 
     * @param listener
     */
    void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Find the index of the given user with the given ID
     *
     * @param userId The user with the id to look for
     * @return the index or -1 if it could not be found
     */
    public int indexOf(int userId) {

        int index = -1;
        for (User s : items) {
            index++;
            if (s.getId() == userId) {
                break;
            }
        }
        return index;
    }

    /**
     * Clear the list of users
     */
    void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    /** 
     * Add new items in this adapter.
     *
     * @param newItems 
     */
    void addItems(List<User> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    /** 
     * Add new items in this adapter.
     *
     * @param newItem
     */
    public void addItem(User newItem) {
        items.add(newItem);
        notifyDataSetChanged();
    }

    /** 
     * Get the initials given the name of the user
     * 
     * @param name
     */
    private String getInitials(String name) {
        return (TextUtils.isEmpty(name)) ? "" : name.substring(0, 1).toUpperCase();
    }


    /** 
     * handleOnClick
     * 
     * @param position
     */
    private void handleOnClick(int position, View transView) {

        if (listener != null) {
            User user = items.get(position);
            listener.onItemClick(user, position, transView);
        }
    }

    /** 
     * The click listener informing a user has been clicked
     */
    interface OnItemClickListener {

        /** 
         * Called when a user has been clicked
         * 
         * @param user
         * @param position 
         * @param view The ImageView for transition
         */
        void onItemClick(User user, int position, View view);
    }

    /**
     *
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        /** */
        final TextView name;

        /** */
        final TextView description;

        /** */
        final TextView initials;

        /**  */
        final ImageView avatar;

        /**
         *
         */
        ViewHolder(View row) {
            super(row);

            name = (TextView) row.findViewById(R.id.text_name);
            description = (TextView) row.findViewById(R.id.text_description);
            initials = (TextView) row.findViewById(R.id.text_initials);
            avatar = (ImageView) row.findViewById(R.id.image_avatar);

            row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleOnClick(getAdapterPosition(), avatar);
                    }
                });
        }
    }
}
