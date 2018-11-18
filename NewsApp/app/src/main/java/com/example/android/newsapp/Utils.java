package com.example.android.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String USGS_REQUEST_URL = "https://content.guardianapis.com/search";

    public static List<NewsArticle> fetchEarthquakeData(String urlString) {
        if(urlString==null || urlString.equals(""))
            return null;

        URL url = createURL(urlString);
        String newsArticleJSON = null;
        try {
            newsArticleJSON = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error in closing InputStream", e);
        }

        List<NewsArticle> articles = null;
        try {
            articles = extractFeatureFromJSON(newsArticleJSON);
        } catch (JSONException | ParseException e) {
            Log.e(LOG_TAG, "Error in parsing JSON", e);
        }
        return articles;
    }

    private static URL createURL(String urlString){
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error in creating URL from String", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        if(url==null)
            return null;

        String newsArticleJSON = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();
            int statusCode = urlConnection.getResponseCode();
            if(statusCode != 200) {
                Log.e(LOG_TAG, "Received status code: "+Integer.toString(statusCode));
                return null;
            }
            else {
                inputStream = urlConnection.getInputStream();
                newsArticleJSON = readFromStream(inputStream);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error in establishing a connection/reading from InputStream", e);
        } finally {
            if(urlConnection!=null)
                urlConnection.disconnect();
            if(inputStream!=null)
                inputStream.close();
        }
        return newsArticleJSON;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        if(inputStream==null)
            return null;
        StringBuilder JSON = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        while(line!=null){
            JSON.append(line);
            line = reader.readLine();
        }
        return JSON.toString();
    }

    private static List<NewsArticle> extractFeatureFromJSON(String NewsArticlesJSON) throws JSONException, ParseException {
        if(NewsArticlesJSON==null)
            return null;

        ArrayList<NewsArticle> articles = new ArrayList<NewsArticle>();
        JSONObject data = new JSONObject(NewsArticlesJSON);
        JSONArray results = data.optJSONObject("response").optJSONArray("results");

        if(results!=null) {
            for (int i = 0; i < results.length(); i++) {
                JSONObject thisResult = results.optJSONObject(i);
                String sectionName = thisResult.optString("sectionName");
                String webPublicationDateString = thisResult.optString("webPublicationDate");
                String webTitle = thisResult.optString("webTitle");
                String webUrl = thisResult.optString("webUrl");
                JSONArray contributors = thisResult.optJSONArray("tags");
                StringBuilder contributorsString = new StringBuilder();
                for (int j = 0; j < contributors.length(); j++) {
                    if(j != 0)
                        contributorsString.append(", ");
                    contributorsString.append(contributors.optJSONObject(j).optString("webTitle"));
                }

                Date webPublicationDate;
                if("".equals(webPublicationDateString))
                    webPublicationDate = null;
                else {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
                    format.setTimeZone(TimeZone.getTimeZone("GMT"));
                    webPublicationDate = format.parse(webPublicationDateString);
                }

                articles.add(new NewsArticle(webTitle, sectionName, contributorsString.toString(), webPublicationDate, webUrl));
            }
        }
        return articles;
    }
}
