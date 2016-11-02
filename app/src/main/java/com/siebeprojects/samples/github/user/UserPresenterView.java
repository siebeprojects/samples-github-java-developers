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

import java.util.List;
import com.siebeprojects.samples.github.model.User;

/**
 * The interface used by the presenter to notify the view
 * to show new content.
 */
interface UserPresenterView {

    /** 
     * Set the user into the view
     * 
     * @param user The user to be set
     */
    void setUser(User user);

    /** 
     * Is the view active, if active then the UI can be populated
     * 
     * @return true when active, false otherwise 
     */
    boolean isActive();

    /** 
     * Notify the view to show a request error 
     */
    void showRequestError();
}
