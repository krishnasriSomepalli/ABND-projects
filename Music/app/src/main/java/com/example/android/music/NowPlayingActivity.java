package com.example.android.music;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NowPlayingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            String song = extras.getString("song");
            String artist = extras.getString("artist");
            String duration = extras.getString("duration");
            int albumArt = extras.getInt("albumArt");

            ((TextView) findViewById(R.id.playing_name)).setText(song);
            ((TextView) findViewById(R.id.playing_artist)).setText(artist);
            ((TextView) findViewById(R.id.playing_duration)).setText(duration);
            ((ImageView) findViewById(R.id.playing_albumArt)).setImageResource(albumArt);
        }
    }
}
