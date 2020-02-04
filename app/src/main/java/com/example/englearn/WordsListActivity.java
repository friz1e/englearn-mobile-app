package com.example.englearn;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class WordsListActivity extends AppCompatActivity {

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    DatabaseHelper wordsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);

        ListView listView = (ListView) findViewById(R.id.wordsListView);
        wordsDatabase = new DatabaseHelper(this);

        ArrayList<String> wordsList = new ArrayList<>();
        Cursor words = wordsDatabase.getWords();

        while(words.moveToNext()) {
            wordsList.add(words.getString(1));
            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, wordsList);
            listView.setAdapter(listAdapter);
        }
    }
}

