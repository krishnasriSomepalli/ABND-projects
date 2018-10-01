package com.example.android.tourguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class LocationAdapter extends ArrayAdapter<Location> {

    public LocationAdapter(Context context, List<Location> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        final Location currentLocation = getItem(position);

        ImageView image = (ImageView) listItemView.findViewById(R.id.image);
        image.setImageResource(currentLocation.getImageId());

        TextView name = (TextView) listItemView.findViewById(R.id.name);
        name.setText(currentLocation.getName());

        TextView address = (TextView) listItemView.findViewById(R.id.address);
        address.setText(currentLocation.getAddress());

        TextView timings = (TextView) listItemView.findViewById(R.id.timings);
        timings.setText(currentLocation.getTimings());

        TextView ratings = (TextView) listItemView.findViewById(R.id.ratings);
        ratings.setText(Float.toString(currentLocation.getRatings())+"/5.0");
        return listItemView;
    }
}
