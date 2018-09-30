package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by HP on 02-06-2018.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int categoryColour;
    private MediaPlayer mediaPlayer = null;
    private int resId = -1;
    private AudioManager am = null;
    private final AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        // Pause playback
                        pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Stop playback, release resources
                        stop();
                        am.abandonAudioFocus(afChangeListener);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback
                        pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        play(resId);
                    }
                }
            };

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }

    private void play(int resId) {
        stop();
        if(mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getContext(), resId);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    stop();
                    am.abandonAudioFocus(afChangeListener);
                }
            });
        }
        mediaPlayer.start();
    }

    private void pause() {
        if(mediaPlayer != null)
            mediaPlayer.pause();
    }

    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            releaseMediaPlayer();
        }
    }

    public WordAdapter(Context context, List<Word> objects, int colour) {
        super(context, 0, objects);
        categoryColour = colour;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        final Word currentWord = getItem(position);
        TextView miwokWord = (TextView) listItemView.findViewById(R.id.miwok_word);
        miwokWord.setText(currentWord.getMiwoktTanslation());
        TextView defaultWord = (TextView) listItemView.findViewById(R.id.default_word);
        defaultWord.setText(currentWord.getDefaultTranslation());
        ImageView listItemIcon = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        if(currentWord.hasImage())
            listItemIcon.setImageResource(currentWord.getImageResolutionId());
        else
            listItemIcon.setVisibility(View.GONE);

        ImageButton playBtn = (ImageButton) listItemView.findViewById(R.id.play_btn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resId = currentWord.getAudioId();
                am = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
                int result = am.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    play(resId);
                }
            }
        });
        FrameLayout btnBg = (FrameLayout) listItemView.findViewById(R.id.btn_bg);
        btnBg.setBackgroundColor(ContextCompat.getColor(getContext(), categoryColour));
        listItemView.findViewById(R.id.text_container).setBackgroundColor(ContextCompat.getColor(getContext(), categoryColour));

        return listItemView;
    }
}
