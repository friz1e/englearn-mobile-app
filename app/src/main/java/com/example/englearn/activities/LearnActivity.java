package com.example.englearn.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.englearn.R;

public class LearnActivity extends AppCompatActivity implements View.OnClickListener {

    private Button englishPolishButton, polishEnglishButton;

    private void setComponents() {
        englishPolishButton = (Button) findViewById(R.id.englishPolishButton);
        polishEnglishButton = (Button) findViewById(R.id.polishEnglishButton);
    }

    private void setOnClickListeners() {
        englishPolishButton.setOnClickListener(this);
        polishEnglishButton.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        setComponents();
        setOnClickListeners();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.englishPolishButton:
                Intent goToEnglishPolishActivity = new Intent(LearnActivity.this, EnglishPolishActivity.class);
                startActivity(goToEnglishPolishActivity);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                break;
            case R.id.polishEnglishButton:
                Intent goToPolishEnglishActivity = new Intent(LearnActivity.this, PolishEnglishActivity.class);
                startActivity(goToPolishEnglishActivity);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                break;
        }
    }


    private float x1, x2, y1, y2;

    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if (x1 > x2) {
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


    public void onBackPressed() {                                                                       
        super.onBackPressed();
        Intent i = new Intent(LearnActivity.this, AddActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }
}

