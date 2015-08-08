package com.gabrielkou.redditdemo.model;

import com.google.api.client.util.Key;

/**
 * Created by gk on 8/7/15.
 */
public class RedditListing {

    @Key
    private String thumbnail;
    @Key
    private String author;
    @Key
    private String title;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
