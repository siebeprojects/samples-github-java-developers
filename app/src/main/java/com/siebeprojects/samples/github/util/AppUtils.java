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

package com.siebeprojects.samples.github.util;

import android.content.Context;

import android.text.TextUtils;
import android.text.format.DateUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.TimeZone;


/**
 * A utility class for manipulating app display values like Dates.
 */
public final class AppUtils {

    /**
     * Converts a long value into  
     * the UTC formatted date String this long value
     * corresponds to.  
     * 
     * @param time      The long value, representing the milliseconds
     *                  since 1.1.1970.
     * @return          The newly created UTC-formatted date string.
     */
    public static String getUTCTimeString(long time) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        f.setTimeZone(TimeZone.getTimeZone("UTC"));
        return f.format(new Date(time)) + "Z";
    }

    /**
     * Converts a long value into a time String.
     *
     * @param context   The context to generate the time value
     * @param time      The long value, representing the
     *                  milliseconds since 1.1.1970.
     * @return          The newly created time string
     */
    public static String getSimpleTimeString(Context context, long time) {
        return DateUtils.formatDateTime(context, time, DateUtils.FORMAT_SHOW_TIME); 
    }

    /**
     * Converts a long value into a date String
     *
     * @param context   The context to generate the date value
     * @param time      The long value, representing the
     *                  milliseconds since 1.1.1970.
     * @return          The newly created date string
     */
    public static String getSimpleDateString(Context context, long time) {
        return DateUtils.formatDateTime(context, time, DateUtils.FORMAT_SHOW_DATE); 
    }

    /** 
     * Get the simple date string given the UTC time.
     * 
     * @param context 
     * @param utcTime The UTC Time string
     * 
     * @return The simple date string or null if it could not be created
     */    
    public static String getSimpleDateString(Context context, String utcTime) {

        if (TextUtils.isEmpty(utcTime)) {
            return null;
        }
        return getSimpleDateString(context, getUTCTimeValue(utcTime));
    }


    /**
     * Parses the UTC time String and return a UTC timestamp
     *
     * @param utcTime the Time String
     * @return the UTC timestamp representation or 0 if 
     *  the time could not be converted
     */
    public static long getUTCTimeValue(String utcTime) {

        if (TextUtils.isEmpty(utcTime)) {
            return 0;
        }
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        f.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date d = f.parse(utcTime, new ParsePosition(0));
        return d == null ? 0 : d.getTime();
    }
}
