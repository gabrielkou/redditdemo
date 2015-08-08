package com.gabrielkou.redditdemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.gabrielkou.redditdemo.model.Reddit;
import com.gabrielkou.redditdemo.model.RedditChild;
import com.gabrielkou.redditdemo.network.RedditSpiceRequest;
import com.octo.android.robospice.JacksonGoogleHttpClientSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView mListView;
    private RedditSpiceRequest redditSpiceRequest;
    private RedditAdapter redditAdapter;
    private LinearLayout progressBarLL;
    private ArrayList<RedditChild> redditChildArrayList=new ArrayList<>();
    private SpiceManager spiceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spiceManager = new SpiceManager(JacksonGoogleHttpClientSpiceService.class);
        setContentView(R.layout.activity_main);
        progressBarLL = (LinearLayout) findViewById(R.id.progressBarLL);
        mListView = (ListView) findViewById(R.id.listview_reddit);
        redditAdapter = new RedditAdapter(this, redditChildArrayList);
        mListView.setAdapter(redditAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RedditChild redditChild = (RedditChild) parent.getItemAtPosition(position);
                Intent sendIntent = new Intent();
                sendIntent.setAction(android.content.Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Reddit Share: author @" + redditChild.getData().getAuthor() + "  title: " + redditChild.getData().getTitle());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.share_this)));
            }
        });
        //Set the default search
        redditSpiceRequest = new RedditSpiceRequest("funny");
        progressBarLL.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.GONE);
        spiceManager.execute(redditSpiceRequest, "json", DurationInMillis.ONE_MINUTE, new RedditSpiceRequestListener());

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!spiceManager.isStarted()){
            spiceManager.start(this);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressBarLL.setVisibility(View.VISIBLE);
                mListView.setVisibility(View.GONE);
                redditSpiceRequest = new RedditSpiceRequest(query);
                spiceManager.execute(redditSpiceRequest, "json", DurationInMillis.ONE_SECOND, new RedditSpiceRequestListener());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    private class RedditSpiceRequestListener implements com.octo.android.robospice.request.listener.RequestListener<com.gabrielkou.redditdemo.model.Reddit> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Log.d(TAG, "Request failure");
            progressBarLL.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, "Request failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(Reddit response) {
            Log.d(TAG, "Request success");
            redditAdapter.clear();
            redditChildArrayList.addAll(response.getData().getChildren());
            progressBarLL.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            redditAdapter.notifyDataSetChanged();
        }
    }
}
