package com.example.android.quakereport;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utils {
    public static final String LOG_TAG = EarthquakeActivity.class.getSimpleName();
    public static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";

    public static List<Earthquake> fetchEarthquakeData(String urlString) {
        if(urlString==null || urlString.equals(""))
            return null;

        URL url = createURL(urlString);
        String EarthquakeJSON = null;
        try {
            EarthquakeJSON = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error in closing InputStream", e);
        }

        List<Earthquake> earthquakes = null;
        try {
            earthquakes = extractFeatureFromJSON(EarthquakeJSON);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error in parsing JSON", e);
        }
        return earthquakes;
    }

    public static URL createURL(String urlString){
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error in creating URL from String", e);
        }
        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        if(url==null)
            return null;

        String EarthquakeJSON = null;
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
                EarthquakeJSON = readFromStream(inputStream);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error in establishing a connection/reading from InputStream", e);
        } finally {
            if(urlConnection!=null)
                urlConnection.disconnect();
            if(inputStream!=null)
                inputStream.close();
        }
        return EarthquakeJSON;
    }

    public static String readFromStream(InputStream inputStream) throws IOException {
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

    public static List<Earthquake> extractFeatureFromJSON(String EarthquakeJSON) throws JSONException {
        if(EarthquakeJSON==null)
            return null;

        ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();
        JSONObject data = new JSONObject(EarthquakeJSON);
        JSONArray features = data.optJSONArray("features");
        if(features!=null) {
            for (int i = 0; i < features.length(); i++) {
                JSONObject thisFeatureProps = features.optJSONObject(i).optJSONObject("properties");
                float mag = (float) thisFeatureProps.optDouble("mag");
                String place = thisFeatureProps.optString("place");
                Date date = new Date((long)thisFeatureProps.optDouble("time"));
                String url =thisFeatureProps.optString("url");

                String primary_location = "";
                String location_offset = "";
                Pattern pattern = Pattern.compile("([0-9]+km [A-Z]+ of )(.*)");
                Matcher matcher = pattern.matcher(place);
                if (matcher.find()) {
                    location_offset = matcher.group(1);
                    primary_location = matcher.group(2);
                }
                else {
                    location_offset = "Near the";
                    primary_location = place;
                }

                earthquakes.add(new Earthquake(mag, primary_location, location_offset, date, url));
            }
        }
        return earthquakes;
    }
}
