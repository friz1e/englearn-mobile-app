package com.example.englearn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class LearnActivity extends AppCompatActivity implements View.OnClickListener {

    Button englishPolishBtn, polishEnglishBtn, wordsListBtn;

    private void setComponents() {
        englishPolishBtn = (Button) findViewById(R.id.englishPolishBtn);
        polishEnglishBtn = (Button) findViewById(R.id.polishEnglishBtn);
        wordsListBtn = (Button) findViewById(R.id.wordsListBtn);
    }

    private void setOnClickListeners() {
        englishPolishBtn.setOnClickListener(this);
        polishEnglishBtn.setOnClickListener(this);
        wordsListBtn.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        setComponents();
        setOnClickListeners();
    }

    private float x1, x2, y1, y2;

    public boolean onTouchEvent(MotionEvent touchEvent) {                                               //swiping mechanism
        switch(touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 > x2) {
                    Intent i = new Intent(LearnActivity.this, AboutActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                } else if (x1 < x2) {
                    Intent i = new Intent(LearnActivity.this, AddActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                }
                break;
        }
        return false;
    }

    public void onBackPressed() {                                                                       //back button functionality
        super.onBackPressed();
        Intent i = new Intent(LearnActivity.this, AddActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.englishPolishBtn:                                                                 //going to EnglishPolishActivity
                Intent goToEnglishPolishActivity = new Intent(LearnActivity.this, EnglishPolishActivity.class);
                startActivity(goToEnglishPolishActivity);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                break;
            case R.id.polishEnglishBtn:                                                                 //going to PolishEnglishActivity
                Intent goToPolishEnglishActivity = new Intent(LearnActivity.this, PolishEnglishActivity.class);
                startActivity(goToPolishEnglishActivity);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                break;
            case R.id.wordsListBtn:
                Intent goToWordsList = new Intent(LearnActivity.this, WordsListActivity.class);
                startActivity(goToWordsList);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
        }

    }
}
