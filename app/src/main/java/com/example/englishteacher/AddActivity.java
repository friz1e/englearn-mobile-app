package com.example.englishteacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddActivity extends AppCompatActivity {

    private Button searchBtn;
    private EditText addANewWordET;

    private void setComponents() {
        addANewWordET = (EditText) findViewById(R.id.addANewWordET);
        searchBtn = (Button) findViewById(R.id.searchBtn);
    }

    private String wordInsertedByUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        setComponents();
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    wordInsertedByUser = addANewWordET.getText().toString();
                    new TranslateAPI().execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}