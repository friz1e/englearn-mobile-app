package com.example.englishteacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class AddActivity extends AppCompatActivity {

    public AddActivity() {

    }

    private Button searchBtn;
    private EditText addANewWordET;

    private void setComponents() {
        addANewWordET = (EditText) findViewById(R.id.addANewWordET);
        searchBtn = (Button) findViewById(R.id.searchBtn);
    }

    public void setWordEquivalentTV(String receivedWord) {
        TextView englishEquivalentTV = (TextView) findViewById(R.id.englishEquivalentTV);
        englishEquivalentTV.setText(receivedWord);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        setComponents();
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String wordInsertedByUser = addANewWordET.getText().toString();
                    TranslateAPI translateAPI = new TranslateAPI(wordInsertedByUser);
                    String result = translateAPI.execute().get();
                    setWordEquivalentTV(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}