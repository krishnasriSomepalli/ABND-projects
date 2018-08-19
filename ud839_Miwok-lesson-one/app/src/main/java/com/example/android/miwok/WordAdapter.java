package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HP on 02-06-2018.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int categoryColour;

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

        Word currentWord = getItem(position);
        TextView miwokWord = (TextView) listItemView.findViewById(R.id.miwok_word);
        miwokWord.setText(currentWord.getMiwoktTanslation());
        TextView defaultWord = (TextView) listItemView.findViewById(R.id.default_word);
        defaultWord.setText(currentWord.getDefaultTranslation());
        ImageView listItemIcon = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        if(currentWord.hasImage())
            listItemIcon.setImageResource(currentWord.getImageResolutionId());
        else
            listItemIcon.setVisibility(View.GONE);

        listItemView.findViewById(R.id.text_container).setBackgroundColor(ContextCompat.getColor(getContext(), categoryColour));

        return listItemView;
    }
}
