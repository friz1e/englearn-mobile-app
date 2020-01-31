package com.example.englishteacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EnglishPolishActivity extends AppCompatActivity {

    TextView questionWordTV, answerWordTV;
    EditText enterET;
    Button startBtn, checkBtn, nextBtn;

    private void setComponents() {
        startBtn = (Button) findViewById(R.id.startBtn);
        questionWordTV = (TextView) findViewById(R.id.questionWordTV);
        answerWordTV = (TextView) findViewById(R.id.answerWordTV);
        enterET = (EditText) findViewById(R.id.enterET);
        checkBtn = (Button) findViewById(R.id.checkBtn);
        nextBtn = (Button) findViewById(R.id.nextBtn);
    }

    DatabaseHelper wordsDatabase;
    String foreignLanguageWord;
    String nativeLanguageWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_polish);

        setComponents();

        questionWordTV.setVisibility(View.INVISIBLE);
        answerWordTV.setVisibility(View.INVISIBLE);
        enterET.setVisibility(View.INVISIBLE);
        checkBtn.setVisibility(View.INVISIBLE);
        nextBtn.setVisibility(View.INVISIBLE);

        wordsDatabase = new DatabaseHelper(this);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor response = wordsDatabase.getRandomWordsPair();
                if(response.moveToFirst()) {
                    do {
                        foreignLanguageWord = response.getString(0);
                        nativeLanguageWord = response.getString(1);
                    } while (response.moveToNext());
                }
                response.close();
                wordsDatabase.close();

                questionWordTV.setText(foreignLanguageWord);
                if(!questionWordTV.getText().toString().equals("")) {
                    startBtn.setVisibility(View.INVISIBLE);
                    questionWordTV.setVisibility(View.VISIBLE);
                    enterET.setVisibility(View.VISIBLE);
                    checkBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        final ColorStateList oldColors = questionWordTV.getTextColors();

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nativeLanguageWord.equals(enterET.getText().toString())) {
                    questionWordTV.setTextColor(Color.GREEN);
                    checkBtn.setVisibility(View.INVISIBLE);
                    nextBtn.setVisibility(View.VISIBLE);
                } else {
                    questionWordTV.setTextColor(Color.RED);
                    answerWordTV.setText(nativeLanguageWord);
                    answerWordTV.setVisibility(View.VISIBLE);
                    checkBtn.setVisibility(View.INVISIBLE);
                    nextBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor response = wordsDatabase.getRandomWordsPair();
                if(response.moveToFirst()) {
                    do {
                        foreignLanguageWord = response.getString(0);
                        nativeLanguageWord = response.getString(1);
                    } while (response.moveToNext());
                }
                response.close();
                wordsDatabase.close();

                questionWordTV.setTextColor(oldColors);
                questionWordTV.setText(foreignLanguageWord);
                answerWordTV.setText("");

                nextBtn.setVisibility(View.INVISIBLE);
                checkBtn.setVisibility(View.VISIBLE);
            }
        });
    }

}
