/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Earthquake>>{

    private EarthquakeAdapter adapter;
    private ListView earthquakeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        getLoaderManager().initLoader(1,null,this);

        adapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());
        earthquakeListView = (ListView) findViewById(R.id.list);
        earthquakeListView.setAdapter(adapter);
    }

    private void updateUI(List<Earthquake> earthquakes) {
        adapter.clear();
        if(earthquakes==null || earthquakes.isEmpty()) {
            earthquakeListView.setEmptyView(findViewById(R.id.empty));
            return;
        }
        adapter.addAll(earthquakes);
        adapter.notifyDataSetChanged();
    }

    private boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        if(isConnected())
            return new EarthquakeLoader(this,Utils.USGS_REQUEST_URL);
        else {
            findViewById(R.id.progress).setVisibility(View.GONE);
            ((TextView)findViewById(R.id.empty)).setText("No internet connection.");
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        findViewById(R.id.progress).setVisibility(View.GONE);
        if(earthquakes==null) {
            ((TextView)findViewById(R.id.empty)).setText("No earthquakes found");
            return;
        }
        updateUI(earthquakes);
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        adapter.clear();
    }
}
