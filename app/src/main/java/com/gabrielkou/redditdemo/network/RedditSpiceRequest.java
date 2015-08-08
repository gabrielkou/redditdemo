package com.gabrielkou.redditdemo.network;

import com.gabrielkou.redditdemo.model.Reddit;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.json.jackson.JacksonFactory;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

import java.io.IOException;



/**
 * Created by gk on 8/7/15.
 */
public class RedditSpiceRequest extends GoogleHttpClientSpiceRequest<Reddit> {
    private String baseUrl;

    public RedditSpiceRequest(String subreddit) {
        super(Reddit.class);
        this.baseUrl = "http://www.reddit.com/r/" + subreddit + "/.json";
    }

    @Override
    public Reddit loadDataFromNetwork() throws IOException {
        HttpRequest request = getHttpRequestFactory()//
                .buildGetRequest( new GenericUrl( baseUrl ) );
        request.setParser( new JacksonFactory().createJsonObjectParser() );
        return request.execute().parseAs(getResultType());
    }
}
