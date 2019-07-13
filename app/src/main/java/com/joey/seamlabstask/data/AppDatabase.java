package com.joey.seamlabstask.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.joey.seamlabstask.data.model.NewsItem;
import com.joey.seamlabstask.data.model.NewsItemDao;

@Database(entities = {NewsItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsItemDao newsItemDao();
}