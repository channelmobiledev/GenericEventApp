package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SPManager {
    private static final String KEY_ACTIVITIES = "activities";
    private static final String KEY_LOCALE = "locale";

    private static final String KEY_ACTIVITIES_DAY_1 = "activities_day_1";
    private static final String KEY_ACTIVITIES_DAY_2 = "activities_day_2";

    private static final String TAG = "SPManager";

    // GENERAL
    public static SharedPreferences getDefaultSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    // LANGUAGE
    public static void setLocale(final Context context, final String locale) {
        SharedPreferences.Editor editor = getDefaultSharedPreferences(context).edit();
        editor.putString(KEY_LOCALE, locale).apply();
    }

    public static String getLocale(final Context context) {
        return getDefaultSharedPreferences(context)
                .getString(KEY_LOCALE, null);
    }

    // ACTIVITIES
    /*
    public static void setActivities(final Context context, final String activitiesList) {
        SharedPreferences.Editor editor = getDefaultSharedPreferences(context).edit();
        editor.putString(KEY_ACTIVITIES, activitiesList).apply();
    }

    public static String getActivities(final Context context) {
        return getDefaultSharedPreferences(context)
                .getString(KEY_ACTIVITIES, null);
    }

    */

    public static void setActivitiesByDay(final Context context, final int day, final String activitiesList) {
        SharedPreferences.Editor editor = getDefaultSharedPreferences(context).edit();

        if (day == 1) {
            editor.putString(KEY_ACTIVITIES_DAY_1, activitiesList).apply();
        } else if (day == 2) {
            editor.putString(KEY_ACTIVITIES_DAY_2, activitiesList).apply();
        }
    }

    public static String getActivitiesByDay(final Context context, int day) {
        if (day == 1) {
            return getDefaultSharedPreferences(context)
                    .getString(KEY_ACTIVITIES_DAY_1, null);
        } else if (day == 2) {
            return getDefaultSharedPreferences(context)
                    .getString(KEY_ACTIVITIES_DAY_2, null);
        }

        return null;
    }
}
