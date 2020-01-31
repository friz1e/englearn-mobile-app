package com.example.englishteacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LearnActivity extends AppCompatActivity implements View.OnClickListener {

    Button englishPolishBtn, polishEnglishBtn;

    private void setComponents() {
        englishPolishBtn = (Button) findViewById(R.id.englishPolishBtn);
        polishEnglishBtn = (Button) findViewById(R.id.polishEnglishBtn);
    }

    private void setOnClickListeners() {
        englishPolishBtn.setOnClickListener(this);
        polishEnglishBtn.setOnClickListener(this);
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
            case R.id.englishPolishBtn:
                Intent goToEnglishPolishActivity = new Intent(LearnActivity.this, EnglishPolishActivity.class);
                startActivity(goToEnglishPolishActivity);
                break;
            case R.id.polishEnglishBtn:
                Intent goToPolishEnglishActivity = new Intent(LearnActivity.this, PolishEnglishActivity.class);
                startActivity(goToPolishEnglishActivity);
                break;
        }

    }
}
