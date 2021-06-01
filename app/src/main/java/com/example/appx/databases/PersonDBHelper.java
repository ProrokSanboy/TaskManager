package com.example.appx.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PersonDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "persons.db";
    public static final int DB_VERSION = 1;

    public PersonDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PersonContract.PersonEntry.CREATE_PERSONS_TABLE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PersonContract.PersonEntry.DROP_COMMAND);
        onCreate(db);
    }
}
