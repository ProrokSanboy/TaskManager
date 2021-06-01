package com.example.appx.databases;

import android.provider.BaseColumns;

public class TaskContract {
    public static final class TasksEntry implements BaseColumns {
        public static String TABLE_NAME = "tasks";
        public static final String ITEM_COLUMN_CONTENT = "content";
        public static final String ITEM_COLUMN_PERSON = "person";
        public static final String ITEM_COLUMN_STATUS = "status";

        public static final String TYPE_TEXT = "TEXT";
        public static final String TYPE_INTEGER = "INTEGER";

        public static final String CREATE_TASKS_TABLE_COMMAND = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" + _ID + " " + TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT, " +
                ITEM_COLUMN_CONTENT + " " + TYPE_TEXT + ", " +
                ITEM_COLUMN_PERSON + " " + TYPE_TEXT + ", " +
                ITEM_COLUMN_STATUS + " " + TYPE_TEXT + " )";

        public static final String DROP_COMMAND = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
