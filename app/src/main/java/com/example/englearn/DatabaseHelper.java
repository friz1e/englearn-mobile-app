package com.example.englearn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wordsDatabase.db";
    private static final String TABLE_NAME = "words";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "FOREIGN_LANGUAGE";
    private static final String COL_3 = "NATIVE_LANGUAGE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, foreign_language TEXT, native_language TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData (String foreignLanguage, String nativeLanguage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, foreignLanguage);
        contentValues.put(COL_3, nativeLanguage);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor checkIsThisWordInTheDatabase(String wordInsertedByUser) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor response = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE FOREIGN_LANGUAGE = '" + wordInsertedByUser + "'", null);
        return response;
    }

    public Cursor getRandomWordsPair() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor response = db.rawQuery("SELECT foreign_language, native_language FROM " + TABLE_NAME + " ORDER BY RANDOM() LIMIT 1", null );
        return response;
    }
    public Cursor getWords() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor response = db.rawQuery("SELECT foreign_language, native_language FROM " + TABLE_NAME, null);
        return response;
    }
}
