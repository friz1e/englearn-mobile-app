package com.example.englishteacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddActivity extends AppCompatActivity {

    public AddActivity() {
    }

    public AddActivity(String received) {
        this.receivedWordFromAPI = received;
    }

    private Button searchBtn;
    private EditText addANewWordET;

    private void setComponents() {
        addANewWordET = (EditText) findViewById(R.id.addANewWordET);
        searchBtn = (Button) findViewById(R.id.searchBtn);
    }

    public String wordInsertedByUser;
    public String receivedWordFromAPI;

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
                    new TranslateAPI(wordInsertedByUser).execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}