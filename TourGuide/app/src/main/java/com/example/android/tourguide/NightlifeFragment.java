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
public class NightlifeFragment extends Fragment {


    public NightlifeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false); //???
        ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(new Location(R.drawable.block_22, getString(R.string.block_22), getString(R.string.loc_block_22), getString(R.string.time_block_22), 4.2f));
        locations.add(new Location(R.drawable.the_tiki_shack, getString(R.string.the_tiki_shack), getString(R.string.loc_the_tiki_shack), getString(R.string.time_the_tiki_shack), 4.1f));
        locations.add(new Location(R.drawable.ml_the_bar_lab, getString(R.string.ml_the_bar_lab), getString(R.string.loc_ml_the_bar_lab), getString(R.string.time_ml_the_bar_lab), 4.1f));
        locations.add(new Location(R.drawable.hyper_local, getString(R.string.hyper_local), getString(R.string.loc_hyper_local), getString(R.string.time_hyper_local), 4.9f));
        ArrayAdapter<Location> itemsAdapter = new LocationAdapter(getActivity(), locations);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        return rootView;
    }

}
