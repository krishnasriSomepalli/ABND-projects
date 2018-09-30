package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.word_list);
        Context context = getApplicationContext();

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("minto wuksus", "Where are you going?", context.getResources().getIdentifier("phrase_where_are_you_going", "raw", getPackageName())));
        words.add(new Word("tinnә oyaase'nә", "What is your name?", context.getResources().getIdentifier("phrase_what_is_your_name", "raw", getPackageName())));
        words.add(new Word("oyaaset...", "My name is...", context.getResources().getIdentifier("phrase_my_name_is", "raw", getPackageName())));
        words.add(new Word("michәksәs?", "How are you feeling?", context.getResources().getIdentifier("phrase_how_are_you_feeling", "raw", getPackageName())));
        words.add(new Word("kuchi achit", "I’m feeling good.", context.getResources().getIdentifier("phrase_im_feeling_good", "raw", getPackageName())));
        words.add(new Word("әәnәs'aa?", "Are you coming?", context.getResources().getIdentifier("phrase_are_you_coming", "raw", getPackageName())));
        words.add(new Word("hәә’ әәnәm", "Yes, I’m coming.", context.getResources().getIdentifier("phrase_yes_im_coming", "raw", getPackageName())));
        words.add(new Word("әәnәm", "I’m coming.", context.getResources().getIdentifier("phrase_im_coming", "raw", getPackageName())));
        words.add(new Word("yoowutis", "Let’s go.", context.getResources().getIdentifier("phrase_lets_go", "raw", getPackageName())));
        words.add(new Word("әnni'nem", "Come here.", context.getResources().getIdentifier("phrase_come_here", "raw", getPackageName())));

        ArrayAdapter<Word> itemsAdapter = new WordAdapter(this, words, R.color.category_phrases);
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
