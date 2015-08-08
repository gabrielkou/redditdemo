package com.gabrielkou.redditdemo.model;

import com.google.api.client.util.Key;

/**
 * Created by gk on 8/7/15.
 */
public class RedditChild {

    @Key
    private String kind;
    @Key
    private RedditListing data;


    public RedditChild() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public RedditListing getData() {
        return data;
    }

    public void setData(RedditListing data) {
        this.data = data;
    }
}
