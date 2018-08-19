package com.example.android.music;

/**
 * Created by HP on 03-06-2018.
 */

public class SongInfo {
    private String song;
    private String artist;
    private long duration;
    private int albumArt;   // drawable resource id

    public SongInfo(String artist, String song, long duration, int albumArt) {
        this.artist = artist;
        this.song = song;
        this.duration = duration;
        this.albumArt = albumArt;
    }
    public String getSong() {
        return song;
    }
    public String getArtist() {
        return artist;
    }
    public long getDuration() {
        return duration;
    }
    public int getAlbumArt() {
        return albumArt;
    }
}
