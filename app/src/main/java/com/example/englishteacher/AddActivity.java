package com.example.englishteacher;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private float x1, x2, y1, y2;

    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch(touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 > x2) {
                    Intent i = new Intent(AddActivity.this, LearnActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                }
                break;
        }
        return false;
    }

    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    private String wordInsertedByUser;
    private String result;

    DatabaseHelper wordsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        wordsDatabase = new DatabaseHelper(this);

        setComponents();
        addingBtn.setVisibility(View.INVISIBLE);
        cancelBtn.setVisibility(View.INVISIBLE);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addANewWordET.getText().toString().length()==0) {
                    Toast.makeText(AddActivity.this, "You haven't entered a word!", Toast.LENGTH_LONG).show();
                }
                else {
                    wordInsertedByUser = addANewWordET.getText().toString();
                    Cursor response = wordsDatabase.checkIsThisWordInTheDatabase(wordInsertedByUser);
                    if (response.getCount() == 1) {
                        Toast.makeText(AddActivity.this, "This word already exists in the database!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        try {
                            TranslateAPI translateAPI = new TranslateAPI(wordInsertedByUser);
                            result = translateAPI.execute().get();
                            setWordEquivalentTV(result);
                            addingBtn.setVisibility(View.VISIBLE);
                            cancelBtn.setVisibility(View.VISIBLE);
                            closeKeyboard();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        addingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    boolean isInserted = wordsDatabase.insertData(wordInsertedByUser, result);
                    if (isInserted == true) {
                        Toast.makeText(AddActivity.this, "Word inserted", Toast.LENGTH_LONG).show();
                        addingBtn.setVisibility(View.INVISIBLE);
                        cancelBtn.setVisibility(View.INVISIBLE);
                        addANewWordET.setText("");
                    } else {
                        Toast.makeText(AddActivity.this, "Word not inserted", Toast.LENGTH_LONG).show();
                    }
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
            }
        });
    }
}