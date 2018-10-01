package com.example.android.tourguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttractionsFragment extends Fragment {


    public AttractionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false); //???
        ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(new Location(R.drawable.skyzone, getString(R.string.skyzone), getString(R.string.loc_skyzone), getString(R.string.time_skyzone), 4.4f));
        locations.add(new Location(R.drawable.soap_football, getString(R.string.soap_football), getString(R.string.loc_soap_football), getString(R.string.time_soap_football), 4.1f));
        locations.add(new Location(R.drawable.lazer_ops, getString(R.string.lazer_ops), getString(R.string.loc_lazer_ops), getString(R.string.time_lazer_ops), 4.3f));
        locations.add(new Location(R.drawable.lumbini_park, getString(R.string.lumbini_park), getString(R.string.loc_lumbini_park), getString(R.string.time_lumbini_park), 4.0f));
        locations.add(new Location(R.drawable.wonderla, getString(R.string.wonderla), getString(R.string.loc_wonderla), getString(R.string.time_wonderla), 4.5f));
        locations.add(new Location(R.drawable.hussain_sagar, getString(R.string.hussain_sagar), getString(R.string.loc_hussain_sagar), getString(R.string.time_hussain_sagar), 4.5f));
        ArrayAdapter<Location> itemsAdapter = new LocationAdapter(getActivity(), locations);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        return rootView;
    }

}
