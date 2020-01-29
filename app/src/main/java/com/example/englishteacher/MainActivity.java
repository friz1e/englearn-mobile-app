package com.example.englishteacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button addBtn, learnBtn, aboutBtn, quitBtn;

    private void setButtons() {
        addBtn = (Button) findViewById(R.id.addBtn);
        learnBtn = (Button) findViewById(R.id.learnBtn);
        aboutBtn = (Button) findViewById(R.id.aboutBtn);
        quitBtn = (Button) findViewById(R.id.quitBtn);
    }

    private void setOnClickListeners() {
        addBtn.setOnClickListener(this);
        learnBtn.setOnClickListener(this);
        aboutBtn.setOnClickListener(this);
        quitBtn.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButtons();
        setOnClickListeners();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addBtn:
                    Intent goToAddActivity = new Intent(MainActivity.this, AddActivity.class);
                    startActivity(goToAddActivity);
                break;
            case R.id.learnBtn:
                    Intent goToLearnActivity = new Intent(MainActivity.this, LearnActivity.class);
                    startActivity(goToLearnActivity);
                break;
            case R.id.aboutBtn:
                    Intent goToAboutActivity = new Intent(MainActivity.this, AboutActivity.class);
                    startActivity(goToAboutActivity);
                break;
            case R.id.quitBtn:
                    finish();
                break;
        }
    }
}
