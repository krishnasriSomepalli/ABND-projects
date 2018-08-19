package com.example.android.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int scoreA = 0;
    private int scoreB = 0;
    WebView teamAImg;
    WebView teamBImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teamAImg = (WebView) findViewById(R.id.team_A_img);
        teamAImg.loadUrl("file:///android_asset/team_A_img.gif");
        teamAImg.setVisibility(View.INVISIBLE);
        teamBImg = (WebView) findViewById(R.id.team_B_img);
        teamBImg.loadUrl("file:///android_asset/team_B_img.gif");
        teamBImg.setVisibility(View.INVISIBLE);
    }
    public void plusOneA(View view) {
        scoreA += 1;
        updateScoreA();
        fireworksA();
    }
    public void plusFourA(View view) {
        scoreA += 4;
        updateScoreA();
        fireworksA();
    }
    public void plusSixA(View view) {
        scoreA += 6;
        updateScoreA();
        fireworksA();
    }
    private void updateScoreA() {
        TextView teamA = (TextView) findViewById(R.id.team_A_score);
        teamA.setText(String.valueOf(scoreA));
    }
    private void fireworksA() {
        teamAImg.setVisibility(View.VISIBLE);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized (this) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.team_A_img).setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        }, 3 * 1000);
    }
    public void plusOneB(View view) {
        scoreB += 1;
        updateScoreB();
        fireworksB();
    }
    public void plusFourB(View view) {
        scoreB += 4;
        updateScoreB();
        fireworksB();
    }
    public void plusSixB(View view) {
        scoreB += 6;
        updateScoreB();
        fireworksB();
    }
    private void updateScoreB() {
        TextView teamB = (TextView) findViewById(R.id.team_B_score);
        teamB.setText(String.valueOf(scoreB));
    }
    private void fireworksB() {
        teamBImg.setVisibility(View.VISIBLE);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized (this) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.team_B_img).setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        }, 3 * 1000);
    }
    public void reset(View view) {
        scoreA = 0;
        scoreB = 0;
        updateScoreA();
        updateScoreB();
    }
}
