package com.joey.seamlabstask.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.joey.seamlabstask.data.db.entities.NewsItem;

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

    @Query("delete FROM newsitem")
    void deleteAll();

    @Delete
    void delete(NewsItem newsItem);
}
