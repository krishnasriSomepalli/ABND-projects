package com.example.android.quakereport;

import java.util.Date;

public class Earthquake {
    private float magnitude;
    private String primary_location;
    private String location_offset;
    private Date date;
    private String url;

    public Earthquake(float magnitude, String primary_location, String location_offset, Date date, String url) {
        this.magnitude = magnitude;
        this.primary_location = primary_location;
        this.location_offset = location_offset;
        this.date = date;
        this.url = url;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public String getPrimaryLocation() {
        return primary_location;
    }

    public String getLocationOffset() {
        return location_offset;
    }

    public Date getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
