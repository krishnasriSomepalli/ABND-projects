package com.example.android.music;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<SongInfo> songs = new ArrayList<SongInfo>();
        songs.add(new SongInfo("Kendric Lamar, SZA", "All The Stars", 249000, R.drawable.all_the_stars));
        songs.add(new SongInfo("Jon Bellion", "All Time Low", 218000, R.drawable.all_time_low));
        songs.add(new SongInfo("The Weeknd", "Can't Feel My Face", 236000, R.drawable.cant_feel_my_face));
        songs.add(new SongInfo("Shakira, Maluma", "Chantaje", 188000, R.drawable.chantaje));
        songs.add(new SongInfo("Nick Jonas, Tove Lo", "Close", 208000, R.drawable.close));
        songs.add(new SongInfo("Halsey", "Colors", 226000, R.drawable.colors));
        songs.add(new SongInfo("Jane XO", "Hard To Forget", 211000, R.drawable.hard_to_forget));
        songs.add(new SongInfo("Fall Out Boy", "I Don't Care", 255000, R.drawable.i_dont_care));
        songs.add(new SongInfo("Grace Vanderwaal", "Moonlight", 201000, R.drawable.moonlight));
        songs.add(new SongInfo("Calvin Harris, Dua Lipa", "One Kiss", 193000, R.drawable.one_kiss));
        songs.add(new SongInfo("Area 21", "Spaceships", 237000, R.drawable.spaceships));
        songs.add(new SongInfo("Hailee Steinfeld, Grey, Zedd", "Starving", 240000, R.drawable.starving));
        songs.add(new SongInfo("KREAM, Clara Mae", "Taped Up Heart", 220000, R.drawable.taped_up_heart));
        songs.add(new SongInfo("Imagine Dragons", "Thunder", 199000, R.drawable.thunder));
        songs.add(new SongInfo("Inna", "Yalla", 166000, R.drawable.yalla));

        SongsInfoAdapter songsAdapter = new SongsInfoAdapter(this, songs);
        ListView songsView = (ListView) findViewById(R.id.listview_songs);
        songsView.setAdapter(songsAdapter);
    }
}
