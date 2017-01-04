package com.quiz.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {
    private static final String PREF_NAME = "user_data";
    private static AppPreference instance;
    private SharedPreferences sh;
    private SharedPreferences.Editor edit;


    public static final String PREF_REGISTER = "UserRegister";
    public static final String PREF_USERNAME = "UserName";
    public static final String PREF_SCORE = "Score";

    public AppPreference(Context mContext) {
        sh = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        edit = sh.edit();
    }

    public static synchronized AppPreference getInstance(Context context) {
        if (instance == null) {
            instance = new AppPreference(context);
        }
        return instance;
    }

    public void setInt(String TAG, int data) {
        edit.putInt(TAG, data).commit();
    }

    public int getInt(String TAG, int defString) {
        return sh.getInt(TAG, defString);
    }

    public void setString(String TAG, String data) {
        edit.putString(TAG, data).commit();
    }

    public String getString(String TAG, String defString) {
        return sh.getString(TAG, defString);
    }

    public void setBoolean(String TAG, boolean value) {
        edit.putBoolean(TAG, value).commit();
    }

    public boolean getBoolean(String TAG, boolean value) {
        return sh.getBoolean(TAG, value);
    }
}
