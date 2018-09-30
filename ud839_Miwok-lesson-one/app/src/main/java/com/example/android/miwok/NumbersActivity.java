package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.word_list);
        Context context = getApplicationContext();

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("lutti", "one", context.getResources().getIdentifier("number_one", "raw", getPackageName()), R.drawable.number_one));
        words.add(new Word("otiiko", "two", context.getResources().getIdentifier("number_two", "raw", getPackageName()), R.drawable.number_two));
        words.add(new Word("tolookosu", "three", context.getResources().getIdentifier("number_three", "raw", getPackageName()), R.drawable.number_three));
        words.add(new Word("oyyisa", "four", context.getResources().getIdentifier("number_four", "raw", getPackageName()), R.drawable.number_four));
        words.add(new Word("massokka", "five", context.getResources().getIdentifier("number_five", "raw", getPackageName()), R.drawable.number_five));
        words.add(new Word("temmokka", "six", context.getResources().getIdentifier("number_six", "raw", getPackageName()), R.drawable.number_six));
        words.add(new Word("kenekaku", "seven", context.getResources().getIdentifier("number_seven", "raw", getPackageName()), R.drawable.number_seven));
        words.add(new Word("kawinta", "eight", context.getResources().getIdentifier("number_eight", "raw", getPackageName()), R.drawable.number_eight));
        words.add(new Word("wo'e", "nine", context.getResources().getIdentifier("number_nine", "raw", getPackageName()), R.drawable.number_nine));
        words.add(new Word("na'aacha", "ten", context.getResources().getIdentifier("number_ten", "raw", getPackageName()), R.drawable.number_ten));

        ArrayAdapter<Word> itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);
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
