package com.example.android.tourguide;

public class Location {
    private int imageId = -1;
    private String name;
    private String address;
    private String timings;
    private float ratings = -1;

    public Location(int imageId, String name, String address, String timings, float ratings) {
        this.imageId = imageId;
        this.name = name;
        this.address = address;
        this.timings = timings;
        this.ratings = ratings;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTimings() {
        return timings;
    }

    public float getRatings() {
        return ratings;
    }
}
