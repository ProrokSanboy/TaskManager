package com.example.appx.databases;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.appx.databases.TaskContract;

public class TasksDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "tasks.db";
    public static final int DB_VERSION = 7;

    public TasksDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TaskContract.TasksEntry.CREATE_TASKS_TABLE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TaskContract.TasksEntry.DROP_COMMAND);
        onCreate(db);
    }
}
