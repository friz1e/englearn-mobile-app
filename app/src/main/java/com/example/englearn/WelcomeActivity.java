package com.example.englearn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {                                                                //welcome screen animation
                Intent intent = new Intent(WelcomeActivity.this, AddActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
                finish();
            }
        }, 1000);
    }
}
