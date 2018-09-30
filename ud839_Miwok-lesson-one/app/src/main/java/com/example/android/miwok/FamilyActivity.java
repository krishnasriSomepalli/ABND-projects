package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.word_list);
        Context context = getApplicationContext();

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("әpә", "father", context.getResources().getIdentifier("family_father", "raw", getPackageName()), R.drawable.family_father));
        words.add(new Word("әṭa", "mother", context.getResources().getIdentifier("family_mother", "raw", getPackageName()), R.drawable.family_mother));
        words.add(new Word("angsi", "son", context.getResources().getIdentifier("family_son", "raw", getPackageName()), R.drawable.family_son));
        words.add(new Word("tune", "daughter", context.getResources().getIdentifier("family_daughter", "raw", getPackageName()), R.drawable.family_daughter));
        words.add(new Word("taachi", "older brother", context.getResources().getIdentifier("family_older_brother", "raw", getPackageName()), R.drawable.family_older_brother));
        words.add(new Word("chalitti", "younger brother", context.getResources().getIdentifier("family_younger_brother", "raw", getPackageName()), R.drawable.family_younger_brother));
        words.add(new Word("teṭe", "older sister", context.getResources().getIdentifier("family_older_sister", "raw", getPackageName()), R.drawable.family_older_sister));
        words.add(new Word("kolliti", "younger sister", context.getResources().getIdentifier("family_younger_sister", "raw", getPackageName()), R.drawable.family_younger_sister));
        words.add(new Word("ama", "grandmother", context.getResources().getIdentifier("family_grandmother", "raw", getPackageName()), R.drawable.family_grandmother));
        words.add(new Word("paapa", "grandfather", context.getResources().getIdentifier("family_grandfather", "raw", getPackageName()), R.drawable.family_grandfather));

        ArrayAdapter<Word> itemsAdapter = new WordAdapter(this, words, R.color.category_family);
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
