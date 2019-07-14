package com.joey.seamlabstask.data.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class NewsItem implements com.joey.seamlabstask.data.model.NewsItem {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;
    @ColumnInfo(name = "description")
    @SerializedName("description")
    private String description;
    @ColumnInfo(name = "content")
    @SerializedName("content")
    private String content;
    @ColumnInfo(name = "urlToImage")
    @SerializedName("urlToImage")
    private String urlToImage;
    @ColumnInfo(name = "publishedAt")
    @SerializedName("publishedAt")
    private String timestamp;

    public NewsItem(){
        this.id = 0;
        this.title = "";
        this.description = "";
        this.content = "";
        this.urlToImage = "";
        this.timestamp = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
