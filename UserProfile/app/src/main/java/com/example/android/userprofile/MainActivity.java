package com.example.android.userprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populate();
    }

    private void populate() {

        TextView name = (TextView) findViewById(R.id.name);
        TextView birthday = (TextView) findViewById(R.id.birthday);
        TextView country = (TextView) findViewById(R.id.country);

        name.setText(R.string.name);
        birthday.setText(R.string.birthday);
        country.setText(R.string.country);

        ImageView profilePicture = (ImageView) findViewById(R.id.profile_picture);

        // source: https://www.thecatalystz.com/testimonials/positivity/
        profilePicture.setImageResource(R.drawable.profile_picture);
    }
}
