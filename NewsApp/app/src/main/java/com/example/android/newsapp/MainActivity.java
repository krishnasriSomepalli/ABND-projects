package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsArticle>>{

    private NewsArticleAdapter adapter;
    private ListView newsArticleListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLoaderManager().initLoader(1,null,this);

        adapter = new NewsArticleAdapter(this, new ArrayList<NewsArticle>());
        newsArticleListView = (ListView) findViewById(R.id.list);
        newsArticleListView.setAdapter(adapter);
    }

    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int i, Bundle bundle) {
        if(isConnected())
            return new NewsArticleLoader(this,Utils.USGS_REQUEST_URL);
        else {
            findViewById(R.id.progress).setVisibility(View.GONE);
            ((TextView)findViewById(R.id.empty)).setText("No internet connection.");
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<List<NewsArticle>> loader, List<NewsArticle> articles) {
        findViewById(R.id.progress).setVisibility(View.GONE);
        if(articles==null) {
            ((TextView)findViewById(R.id.empty)).setText("No articles found");
            return;
        }
        updateUI(articles);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsArticle>> loader) {
        adapter.clear();
    }

    private void updateUI(List<NewsArticle> articles) {
        adapter.clear();
        if(articles==null || articles.isEmpty()) {
            newsArticleListView.setEmptyView(findViewById(R.id.empty));
            return;
        }
        adapter.addAll(articles);
        adapter.notifyDataSetChanged();
    }

    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
            return isConnected;
        }
        catch(NullPointerException e) {
            Log.e(Utils.LOG_TAG, "Error in detecting network", e);
            return false;
        }
    }
}
