package com.example.englearn.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englearn.animations.Animations;
import com.example.englearn.database.DatabaseHelper;
import com.example.englearn.R;
import com.example.englearn.api.TranslateAPI;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    public AddActivity() {

    }

    private Button searchButton, addingButton, cancelButton;
    private EditText addANewWordEditText;

    private void setComponents() {
        searchButton = (Button) findViewById(R.id.searchButton);
        addingButton = (Button) findViewById(R.id.addingButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        addANewWordEditText = (EditText) findViewById(R.id.addANewWordEditText);

        addingButton.setVisibility(View.INVISIBLE);
        cancelButton.setVisibility(View.INVISIBLE);
    }

    private void setOnClickListeners() {
        searchButton.setOnClickListener(this);
        addingButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setComponents();
        setOnClickListeners();
    }



    private TextView englishEquivalentTextView;

    private void setEnglishEquivalentTextView(String receivedWord) {
        englishEquivalentTextView = (TextView) findViewById(R.id.englishEquivalentTextView);
        englishEquivalentTextView.setText(receivedWord);
    }

    private Animations animations = new Animations();

    private String wordInsertedByUser;
    private String resultReturnedByAPI;

    DatabaseHelper wordsDatabase;

    @Override
    public void onClick(View v) {

        wordsDatabase = new DatabaseHelper(this);

        switch (v.getId()) {
            case R.id.searchButton:
                int isAddANewWordEditTextEmpty = addANewWordEditText.getText().toString().length();
                int empty = 0;

                if (isAddANewWordEditTextEmpty == empty) {
                    Toast.makeText(AddActivity.this, "You haven't entered a word!", Toast.LENGTH_LONG).show();
                } else {
                    wordInsertedByUser = addANewWordEditText.getText().toString();
                    Cursor responseFromDatabase = wordsDatabase.checkIsThisWordInTheDatabase(wordInsertedByUser);

                    int doesThatWordExistsInDatabase = responseFromDatabase.getCount();
                    int wordExists = 1;

                    if (doesThatWordExistsInDatabase == wordExists) {
                        Toast.makeText(AddActivity.this, "This word already exists in the database!", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            TranslateAPI translateAPI = new TranslateAPI(wordInsertedByUser);
                            resultReturnedByAPI = translateAPI.execute().get();

                            setEnglishEquivalentTextView(resultReturnedByAPI);

                            animations.fadeOutAnimationForButton(searchButton);
                            animations.fadeInAnimationForTextView(englishEquivalentTextView);
                            animations.fadeInAnimationForButton(addingButton);
                            animations.fadeInAnimationForButton(cancelButton);
                            closeKeyboard();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;

            case R.id.addingButton:
                boolean areWordsInsertedToDatabase = wordsDatabase.insertData(wordInsertedByUser, resultReturnedByAPI);

                if (areWordsInsertedToDatabase == true) {
                    Toast.makeText(AddActivity.this, "Word inserted", Toast.LENGTH_LONG).show();

                    animations.fadeInAnimationForButton(searchButton);
                    animations.fadeOutAnimationForButton(addingButton);
                    animations.fadeOutAnimationForButton(cancelButton);
                    animations.fadeOutAnimationForTextView(englishEquivalentTextView);
                    addANewWordEditText.setText("");
                } else {
                    Toast.makeText(AddActivity.this, "Word not inserted", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.cancelButton:
                wordInsertedByUser = addANewWordEditText.getText().toString();
                englishEquivalentTextView.getText().toString();

                wordInsertedByUser = null;
                englishEquivalentTextView = null;

                animations.fadeInAnimationForButton(searchButton);
                animations.fadeOutAnimationForButton(addingButton);
                animations.fadeOutAnimationForButton(cancelButton);
                animations.fadeOutAnimationForTextView(englishEquivalentTextView);
                addANewWordEditText.setText("");
                break;
        }
    }


    private float x1, x2, y1, y2;

    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if (x1 > x2) {
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

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}