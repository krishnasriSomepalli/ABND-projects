package com.example.android.music;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by HP on 03-06-2018.
 */

public class SongsInfoAdapter extends ArrayAdapter<SongInfo> {

    public SongsInfoAdapter(Context context, ArrayList<SongInfo> songs) {
        super(context, 0, songs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View songView = convertView;
        if(null == songView)
            songView = (LayoutInflater.from(getContext())).inflate(R.layout.activity_song, parent, false);

        final SongInfo currentSong = getItem(position);

        ImageView albumArt = songView.findViewById(R.id.song_albumArt);
        albumArt.setImageResource(currentSong.getAlbumArt());

        TextView songName = songView.findViewById(R.id.song_name);
        songName.setText(currentSong.getSong());

        TextView songArtist = songView.findViewById(R.id.song_artist);
        songArtist.setText(currentSong.getArtist());

        TextView songDuration = songView.findViewById(R.id.song_duration);
        final String duration = String.format("%d:%02d",
                        TimeUnit.MILLISECONDS.toSeconds(currentSong.getDuration())/60,
                        TimeUnit.MILLISECONDS.toSeconds(currentSong.getDuration()) % 60 );
        songDuration.setText(duration);

        LinearLayout song = songView.findViewById(R.id.song);
        song.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent nowPlayingIntent = new Intent(getContext(), NowPlayingActivity.class);
                nowPlayingIntent.putExtra("song", currentSong.getSong());
                nowPlayingIntent.putExtra("artist", currentSong.getArtist());
                nowPlayingIntent.putExtra("duration", duration);
                nowPlayingIntent.putExtra("albumArt", currentSong.getAlbumArt());
                getContext().startActivity(nowPlayingIntent);
            }
        });

        return songView;
    }
}
