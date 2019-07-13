package com.joey.seamlabstask;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MySettings {
    private static final String TAG = MySettings.class.getSimpleName();

    private static final String PREF_CACHED_NEWS = "pref_cached_news";


    private static SharedPreferences sharedPref;
    private static JsonObject cachedOrder;

    private static long activeOrderCustomerID;

    private static Gson gson;

    private MySettings(){

    }

    public static void setCachedOrder(JsonObject order) {
        MySettings.cachedOrder = order;

        SharedPreferences.Editor editor = getSettings().edit();
        if(cachedOrder != null) {
            editor.putString(PREF_CACHED_NEWS, cachedOrder.toString());
        }else{
            editor.putString(PREF_CACHED_NEWS, "");
        }
        editor.apply();
    }
    public static JsonObject getCachedOrder() {
        if (cachedOrder != null) {
            return cachedOrder;
        } else {
            SharedPreferences prefs = getSettings();
            String cachedOrderString = prefs.getString(PREF_CACHED_NEWS, "");
            if(cachedOrderString.length() >= 1){
                JsonParser parser = new JsonParser();
                cachedOrder = parser.parse(cachedOrderString).getAsJsonObject();
            }
            return cachedOrder;
        }
    }

    public static SharedPreferences getSettings() {
        if(sharedPref == null){
            sharedPref = MyApp.getShardPrefs();
        }

        return sharedPref;
    }
}
