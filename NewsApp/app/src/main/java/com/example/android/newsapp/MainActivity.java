package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsArticle>>{

    private NewsArticleAdapter adapter;
    private ListView newsArticleListView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

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
        if(isConnected()) {
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

            String searchTerm = sharedPrefs.getString(
                    getString(R.string.settings_query_key),
                    getString(R.string.settings_query_default));

            String orderBy  = sharedPrefs.getString(
                    getString(R.string.settings_order_by_key),
                    getString(R.string.settings_order_by_default)
            );

            Uri baseUri = Uri.parse(Utils.USGS_REQUEST_URL);
            Uri.Builder uriBuilder = baseUri.buildUpon();

            uriBuilder.appendQueryParameter("q", searchTerm);
            uriBuilder.appendQueryParameter("show-tags", "contributor");
            uriBuilder.appendQueryParameter("order-by", orderBy);
            uriBuilder.appendQueryParameter("api-key", getString(R.string.api_key));

            return new NewsArticleLoader(this, uriBuilder.toString());
        }
        else {
            findViewById(R.id.progress).setVisibility(View.GONE);
            ((TextView)findViewById(R.id.empty)).setText(com.example.android.newsapp.R.string.no_connection_message);
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<List<NewsArticle>> loader, List<NewsArticle> articles) {
        findViewById(R.id.progress).setVisibility(View.GONE);
        if(articles==null) {
            ((TextView)findViewById(R.id.empty)).setText(com.example.android.newsapp.R.string.no_data_message);
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
