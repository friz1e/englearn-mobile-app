package com.example.englearn.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.englearn.database.DatabaseHelper;
import com.example.englearn.R;

public class PolishEnglishActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionWordTextView, answerWordTextView;
    private EditText enterEditText;
    private Button startButton, checkButton, nextButton;

    private void setComponents() {
        startButton = (Button) findViewById(R.id.startButton);
        checkButton = (Button) findViewById(R.id.checkButton);
        nextButton = (Button) findViewById(R.id.nextButton);

        questionWordTextView = (TextView) findViewById(R.id.questionWordTextView);
        questionWordTextView.setTextColor(Color.parseColor("#fafafa"));

        answerWordTextView = (TextView) findViewById(R.id.answerWordTextView);

        enterEditText = (EditText) findViewById(R.id.enterEditText);

        questionWordTextView.setVisibility(View.INVISIBLE);
        enterEditText.setVisibility(View.INVISIBLE);
        checkButton.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);
    }

    private void setOnClickListeners() {
        startButton.setOnClickListener(this);
        checkButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polish_english);

        setComponents();
        setOnClickListeners();
    }



    private DatabaseHelper wordsDatabase;
    private String foreignLanguageWord;
    private String nativeLanguageWord;

    @Override
    public void onClick(View v) {

        Cursor responseFromDatabase;
        wordsDatabase = new DatabaseHelper(this);

        switch(v.getId()) {
            case R.id.startButton:
                questionWordTextView.getTextColors();
                responseFromDatabase = wordsDatabase.getRandomWordsPair();
                if(responseFromDatabase.moveToFirst()) {
                    do {
                        foreignLanguageWord = responseFromDatabase.getString(0);
                        nativeLanguageWord = responseFromDatabase.getString(1);
                    } while (responseFromDatabase.moveToNext());
                }
                responseFromDatabase.close();
                wordsDatabase.close();

                questionWordTextView.setText(nativeLanguageWord);

                boolean ifQuestionWordTextViewIsEmpty = questionWordTextView.getText().toString().equals("");
                if(!ifQuestionWordTextViewIsEmpty) {
                    startButton.setVisibility(View.INVISIBLE);
                    questionWordTextView.setVisibility(View.VISIBLE);
                    enterEditText.setVisibility(View.VISIBLE);
                    checkButton.setVisibility(View.VISIBLE);
                }
                break;


            case R.id.checkButton:
                boolean ifUserEnteredCorrectAnswer = foreignLanguageWord.equals(enterEditText.getText().toString());
                if (ifUserEnteredCorrectAnswer) {
                    questionWordTextView.setTextColor(Color.GREEN);
                    checkButton.setVisibility(View.INVISIBLE);
                    nextButton.setVisibility(View.VISIBLE);
                } else {
                    questionWordTextView.setTextColor(Color.RED);
                    answerWordTextView.setText(foreignLanguageWord);
                    answerWordTextView.setVisibility(View.VISIBLE);
                    checkButton.setVisibility(View.INVISIBLE);
                    nextButton.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.nextButton:
                responseFromDatabase = wordsDatabase.getRandomWordsPair();
                if(responseFromDatabase.moveToFirst()) {
                    do {
                        foreignLanguageWord = responseFromDatabase.getString(0);
                        nativeLanguageWord = responseFromDatabase.getString(1);
                    } while (responseFromDatabase.moveToNext());
                }
                responseFromDatabase.close();
                wordsDatabase.close();

                questionWordTextView.setTextColor(Color.parseColor("#fafafa"));
                questionWordTextView.setText(nativeLanguageWord);
                answerWordTextView.setText("");


                nextButton.setVisibility(View.INVISIBLE);
                checkButton.setVisibility(View.VISIBLE);
                break;
        }
    }



    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }
}