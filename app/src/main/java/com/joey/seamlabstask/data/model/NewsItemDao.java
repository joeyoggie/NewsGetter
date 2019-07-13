package com.joey.seamlabstask.data.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface NewsItemDao {
    @Query("SELECT * FROM newsitem")
    LiveData<List<NewsItem>> getAll();

    @Query("SELECT * FROM newsitem WHERE id =:newsID LIMIT 1")
    LiveData<NewsItem> findByID(long newsID);

    @Insert(onConflict = REPLACE)
    void insertAll(NewsItem... newsItems);

    @Delete
    void delete(NewsItem newsItem);
}
