package com.example.englearn.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.englearn.R;

public class AboutActivity extends AppCompatActivity {

    private float x1, x2, y1, y2;                                                                        //co-ordinates

    public boolean onTouchEvent(MotionEvent touchEvent) {                                               // swiping mechanism
        switch(touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 < x2) {
                    Intent i = new Intent(AboutActivity.this, LearnActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                }
                break;
        }
        return false;
    }

    public void onBackPressed() {                                                                       //back button functionality
        super.onBackPressed();
        Intent i = new Intent(AboutActivity.this, LearnActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
