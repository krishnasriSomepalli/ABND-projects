package com.example.android.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logMenu(View view) {
        TextView item1 = (TextView) findViewById(R.id.item1);
        TextView item2 = (TextView) findViewById(R.id.item2);
        TextView item3 = (TextView) findViewById(R.id.item3);

        Log.i("MainActivity.java", item1.getText().toString()+"\n"+item2.getText().toString()+"\n"+item3.getText().toString()+"\n");
    }
}
