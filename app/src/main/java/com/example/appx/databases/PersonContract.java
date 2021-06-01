package com.example.appx.databases;

import android.provider.BaseColumns;

public class PersonContract {
    public static final class PersonEntry implements BaseColumns {
        public static final String TABLE_NAME = "persons";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_POST = "post";
        public static final String COLUMN_LOGIN = "login";
        public static final String COLUMN_PASSWORD = "password";

        public static final String TYPE_TEXT = "TEXT";
        public static final String TYPE_INTEGER = "INTEGER";

        public static final String CREATE_PERSONS_TABLE_COMMAND = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" + _ID + " " + TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " " + TYPE_TEXT + ", " +
                COLUMN_SURNAME + " " + TYPE_TEXT + ", " +
                COLUMN_POST + " " + TYPE_TEXT + ", " +
                COLUMN_LOGIN + " " + TYPE_TEXT + ", " +
                COLUMN_PASSWORD + " " + TYPE_TEXT + " )";

        public static final String DROP_COMMAND = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
