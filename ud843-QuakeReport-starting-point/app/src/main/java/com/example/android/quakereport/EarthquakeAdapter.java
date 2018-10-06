package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(Context context, List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Earthquake eq = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        LinearLayout listItem = (LinearLayout) convertView.findViewById(R.id.list_item);
        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(eq.getUrl()));

                if (sendIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    getContext().startActivity(sendIntent);
                }
            }
        });

        TextView magnitude = (TextView) convertView.findViewById(R.id.magnitude);
        magnitude.setText((new DecimalFormat("0.0")).format((double)eq.getMagnitude()));
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(eq.getMagnitude());
        magnitudeCircle.setColor(getContext().getResources().getColor(magnitudeColor));

        TextView locationOffset = (TextView) convertView.findViewById(R.id.location_offset);
        locationOffset.setText(eq.getLocationOffset());

        TextView primaryLocation = (TextView) convertView.findViewById(R.id.primary_location);
        primaryLocation.setText(eq.getPrimaryLocation());

        TextView date = (TextView) convertView.findViewById(R.id.date);
        date.setText((new SimpleDateFormat("MMM dd, yyyy", Locale.US)).format(eq.getDate()));

        TextView time = (TextView) convertView.findViewById(R.id.time);
        time.setText((new SimpleDateFormat("h:mm a", Locale.US)).format(eq.getDate()));

        return convertView;
    }

    private int getMagnitudeColor(float mag) {
        int magFloor = (int) Math.floor(mag);
        int bgColor;
        switch (magFloor) {
            case 0:
            case 1:
                bgColor = R.color.magnitude1;
                break;
            case 2:
                bgColor = R.color.magnitude2;
                break;
            case 3:
                bgColor = R.color.magnitude3;
                break;
            case 4:
                bgColor = R.color.magnitude4;
                break;
            case 5:
                bgColor = R.color.magnitude5;
                break;
            case 6:
                bgColor = R.color.magnitude6;
                break;
            case 7:
                bgColor = R.color.magnitude7;
                break;
            case 8:
                bgColor = R.color.magnitude8;
                break;
            case 9:
                bgColor = R.color.magnitude9;
                break;
            default:
                bgColor = R.color.magnitude10plus;
                break;
        }
        return bgColor;
    }
}
