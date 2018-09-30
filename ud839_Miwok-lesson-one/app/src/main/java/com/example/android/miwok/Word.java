package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

/**
 * Created by HP on 02-06-2018.
 */

public class Word {
    private String mMiwoktTanslation;
    private String mDefaultTranslation;
    private int mAudioId = -1;
    private int mImageResolutionId = -1;

    public Word(String miwokTranslation, String defaultTranslation, int audioId) {
        mMiwoktTanslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mAudioId = audioId;
    }
    public Word(String miwokTranslation, String defaultTranslation, int audioId, int imageResolutionId) {
        mMiwoktTanslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mAudioId = audioId;
        mImageResolutionId = imageResolutionId;
    }
    public String getMiwoktTanslation() {
        return mMiwoktTanslation;
    }
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }
    public int getAudioId() {return mAudioId;}
    public int getImageResolutionId() {return mImageResolutionId; }
    public boolean hasImage() {return mImageResolutionId != -1; }
}
