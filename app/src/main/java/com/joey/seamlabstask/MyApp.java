package com.joey.seamlabstask;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.joey.seamlabstask.data.AppDatabase;

public class MyApp extends Application {
    private static MyApp mInstance;

    private static AppDatabase mDatabase;


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

    public static AppDatabase initDB(){
        if(mDatabase != null){
            return mDatabase;
        }else{
            mDatabase = Room.databaseBuilder(MyApp.getInstance(), AppDatabase.class, Constants.DB_NAME)
                    .allowMainThreadQueries().
                            build();
            return mDatabase;
        }
    }
}
