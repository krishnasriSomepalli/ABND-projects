package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class NewsArticleLoader extends AsyncTaskLoader<List<NewsArticle>> {

    private String urlString;

    public NewsArticleLoader(Context context, String urlString){
        super(context);
        this.urlString = urlString;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsArticle> loadInBackground() {
        if(urlString==null)
            return null;
        return Utils.fetchEarthquakeData(urlString);
    }
}
