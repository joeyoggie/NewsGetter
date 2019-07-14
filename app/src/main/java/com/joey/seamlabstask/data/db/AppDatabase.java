package com.joey.seamlabstask.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.joey.seamlabstask.data.db.entities.NewsItem;
import com.joey.seamlabstask.data.db.dao.NewsItemDao;

@Database(entities = {NewsItem.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsItemDao newsItemDao();
}