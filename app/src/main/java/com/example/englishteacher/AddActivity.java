package com.example.englishteacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity  {

    public AddActivity() {

    }

    private Button searchBtn;
    private EditText addANewWordET;
    private TextView englishEquivalentTV;
    private Button addingBtn;
    private Button cancelBtn;

    private void setComponents() {
        searchBtn = (Button) findViewById(R.id.searchBtn);
        addANewWordET = (EditText) findViewById(R.id.addANewWordET);
        addingBtn = (Button) findViewById(R.id.addingBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);
    }

    public void setWordEquivalentTV(String receivedWord) {
        englishEquivalentTV = (TextView) findViewById(R.id.englishEquivalentTV);
        englishEquivalentTV.setText(receivedWord);
    }

    private String wordInsertedByUser;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        setComponents();
        addingBtn.setVisibility(View.INVISIBLE);
        cancelBtn.setVisibility(View.INVISIBLE);
        addingBtn.setEnabled(false);
        cancelBtn.setEnabled(false);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    wordInsertedByUser = addANewWordET.getText().toString();
                    TranslateAPI translateAPI = new TranslateAPI(wordInsertedByUser);
                    result = translateAPI.execute().get();
                    setWordEquivalentTV(result);
                    if(addANewWordET.getText().length()==0) {
                        addingBtn.setVisibility(View.INVISIBLE);
                        cancelBtn.setVisibility(View.INVISIBLE);
                        addingBtn.setEnabled(false);
                        cancelBtn.setEnabled(false);
                    } else {
                        addingBtn.setVisibility(View.VISIBLE);
                        cancelBtn.setVisibility(View.VISIBLE);
                        addingBtn.setEnabled(true);
                        cancelBtn.setEnabled(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        addingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordInsertedByUser = addANewWordET.getText().toString();
                englishEquivalentTV.getText().toString();
                wordInsertedByUser = null;
                result = null;
                addANewWordET.setText("");
                englishEquivalentTV.setText("");
                addingBtn.setVisibility(View.INVISIBLE);
                cancelBtn.setVisibility(View.INVISIBLE);
                addingBtn.setEnabled(false);
                cancelBtn.setEnabled(false);
            }
        });
    }
}