package com.example.android.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void play(View v) {
        if(mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier("classic", "raw", getPackageName()));
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(MainActivity.this, "I'm done!", Toast.LENGTH_SHORT).show();
                }
            });
            mediaPlayer.start();
        }
        else if(!mediaPlayer.isPlaying())
            mediaPlayer.start();
    }
    public void pause(View v) {
        mediaPlayer.pause();
    }
    public void startOver(View v) {
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
    }
}
