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
public class HistoricalSitesFragment extends Fragment {


    public HistoricalSitesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false); //???
        ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(new Location(R.drawable.charminar, getString(R.string.charminar), getString(R.string.loc_charminar), getString(R.string.time_charminar), 4.4f));
        locations.add(new Location(R.drawable.chowmahalla_1, getString(R.string.chowmahalla), getString(R.string.loc_chowmahalla), getString(R.string.time_chowmahalla), 4.4f));
        locations.add(new Location(R.drawable.falaknuma_palace, getString(R.string.falaknuma_palace), getString(R.string.loc_falaknuma_palace), getString(R.string.time_falaknuma_palace), 4.6f));
        locations.add(new Location(R.drawable.taramati, getString(R.string.taramati), getString(R.string.loc_taramati), getString(R.string.time_taramati), 4.0f));
        locations.add(new Location(R.drawable.golconda_fort, getString(R.string.golconda_fort), getString(R.string.loc_golconda_fort), getString(R.string.time_golconda_fort), 4.4f));
        ArrayAdapter<Location> itemsAdapter = new LocationAdapter(getActivity(), locations);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        return rootView;
    }

}
