package com.gabrielkou.redditdemo.model;

import com.google.api.client.util.Key;

/**
 * Created by gk on 8/7/15.
 */
public class Reddit {
    @Key
    private String kind;
    @Key
    private RedditData data;

    public Reddit() {
    }

    public String getKind() {
        return this.kind;
    }

    public RedditData getData() {
        return this.data;
    }

}
