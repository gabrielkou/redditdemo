package com.gabrielkou.redditdemo.model;

import com.google.api.client.util.Key;

import java.util.ArrayList;

/**
 * Created by gk on 8/7/15.
 */
public class RedditData {
    @Key
    private String modhash;
    @Key
    private ArrayList<RedditChild> children;
    @Key
    private String after;
    @Key
    private String before;

    public RedditData() {
    }

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public ArrayList<RedditChild> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<RedditChild> children) {
        this.children = children;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }
}
