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
public class FoodAndDrinkFragment extends Fragment {


    public FoodAndDrinkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false); //???
        ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(new Location(R.drawable.flechazo, getString(R.string.flechazo), getString(R.string.loc_flechazo), getString(R.string.time_flechazo), 4.1f));
        locations.add(new Location(R.drawable.sheraton, getString(R.string.sheraton), getString(R.string.loc_sheraton), getString(R.string.time_sheraton), 4.3f));
        locations.add(new Location(R.drawable.concu, getString(R.string.concu), getString(R.string.loc_concu), getString(R.string.time_concu), 4.6f));
        locations.add(new Location(R.drawable.eat_confetti, getString(R.string.eat_confetti), getString(R.string.loc_eat_confetti), getString(R.string.time_eat_confetti), 3.9f));
        locations.add(new Location(R.drawable.gallery_cafe, getString(R.string.gallery_cafe), getString(R.string.loc_gallery_cafe), getString(R.string.time_gallery_cafe), 4.2f));
        locations.add(new Location(R.drawable.the_matter_of_batter, getString(R.string.the_matter_of_batter), getString(R.string.loc_the_matter_of_batter), getString(R.string.time_the_matter_of_batter), 4.2f));
        ArrayAdapter<Location> itemsAdapter = new LocationAdapter(getActivity(), locations);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        return rootView;
    }

}
