package com.example.panicbutton.models;

import android.database.sqlite.*;
import android.provider.BaseColumns;

public class SQLContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private SQLContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "contacts";
        public static final String COLUMN_NAME= "name";
        public static final String COLUMN_PHONE = "phone";
    }

    public static String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                        FeedEntry._ID + " INTEGER PRIMARY KEY," +
                        FeedEntry.COLUMN_NAME + " TEXT," +
                        FeedEntry.COLUMN_PHONE + " TEXT)";

    public static String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
}
