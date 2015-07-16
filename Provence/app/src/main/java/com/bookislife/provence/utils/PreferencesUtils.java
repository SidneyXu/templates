package com.bookislife.provence.utils;

import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

public class PreferencesUtils {

    public static void putJSON(SharedPreferences preferences,
                               String key,
                               JSONObject jsonObject) {
        preferences.edit().putString(key, jsonObject.toString()).apply();
    }

    public static JSONObject loadJSON(SharedPreferences preferences,
                                      String key) {
        String jsonStr = preferences.getString(key, null);
        if (jsonStr == null) {
            return null;
        }
        try {
            return new JSONObject(jsonStr);
        } catch (JSONException e) {
            return null;
        }
    }
}
