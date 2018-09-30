package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.word_list);
        Context context = getApplicationContext();

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("weṭeṭṭi", "red", context.getResources().getIdentifier("color_red", "raw", getPackageName()), R.drawable.color_red));
        words.add(new Word("chokokki", "green", context.getResources().getIdentifier("color_green", "raw", getPackageName()), R.drawable.color_green));
        words.add(new Word("ṭakaakki", "brown", context.getResources().getIdentifier("color_brown", "raw", getPackageName()), R.drawable.color_brown));
        words.add(new Word("ṭopoppi", "gray", context.getResources().getIdentifier("color_gray", "raw", getPackageName()), R.drawable.color_gray));
        words.add(new Word("kululli", "black", context.getResources().getIdentifier("color_black", "raw", getPackageName()), R.drawable.color_black));
        words.add(new Word("kelelli", "white", context.getResources().getIdentifier("color_white", "raw", getPackageName()), R.drawable.color_white));
        words.add(new Word("ṭopiisә", "dusty yellow", context.getResources().getIdentifier("color_dusty_yellow", "raw", getPackageName()), R.drawable.color_dusty_yellow));
        words.add(new Word("chiwiiṭә", "mustard yellow", context.getResources().getIdentifier("color_mustard_yellow", "raw", getPackageName()), R.drawable.color_mustard_yellow));

        ArrayAdapter<Word> itemsAdapter = new WordAdapter(this, words, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
