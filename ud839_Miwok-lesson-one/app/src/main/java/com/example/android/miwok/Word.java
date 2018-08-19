package com.example.android.miwok;

/**
 * Created by HP on 02-06-2018.
 */

public class Word {
    private String mMiwoktTanslation;
    private String mDefaultTranslation;
    private int mImageResolutionId = -1;

    public Word(String miwokTranslation, String defaultTranslation) {
        mMiwoktTanslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
    }
    public Word(String miwokTranslation, String defaultTranslation, int imageResolutionId) {
        mMiwoktTanslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mImageResolutionId = imageResolutionId;
    }
    public String getMiwoktTanslation() {
        return mMiwoktTanslation;
    }
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }
    public int getImageResolutionId() {return mImageResolutionId; }
    public boolean hasImage() {return mImageResolutionId != -1; }
}
