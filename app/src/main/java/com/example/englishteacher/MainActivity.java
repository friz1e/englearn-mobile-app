package com.example.englishteacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addBtn, learnBtn, aboutBtn, quitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setButtons() {
        addBtn = (Button) findViewById(R.id.addBtn);
        learnBtn = (Button) findViewById(R.id.addBtn);
        aboutBtn = (Button) findViewById(R.id.addBtn);
        quitBtn = (Button) findViewById(R.id.addBtn);
    }
}
