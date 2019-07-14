package com.joey.seamlabstask;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class MyApp extends Application {
    private static MyApp mInstance;


    @Override
    public void onCreate() {
        super.onCreate();

        /*CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/arial.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );*/

        mInstance = this;
    }

    public static synchronized MyApp getInstance() {
        return mInstance;
    }

    public static SharedPreferences getShardPrefs(){
        SharedPreferences prefs = mInstance.getSharedPreferences(Constants.PACKAGE_NAME, Context.MODE_PRIVATE);
        return prefs;
    }
}
