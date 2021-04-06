package com.example.panicbutton.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class SQLManager {
    private SQLHelper dbHelper;
    private SQLiteDatabase db;

    SQLManager(Context context) {
        dbHelper = new SQLHelper(context);
        db = dbHelper.getReadableDatabase();
    }

    public ArrayList<ContactModel> getContacts() {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                SQLContract.FeedEntry.COLUMN_NAME,
                SQLContract.FeedEntry.COLUMN_PHONE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = SQLContract.FeedEntry.COLUMN_PHONE + " <> ?";
        String[] selectionArgs = { "0" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                SQLContract.FeedEntry.COLUMN_NAME + " DESC";

        Cursor cursor = db.query(
                SQLContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<ContactModel> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            String contactName = cursor.getString(cursor.getColumnIndexOrThrow(SQLContract.FeedEntry.COLUMN_NAME));
            String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(SQLContract.FeedEntry.COLUMN_PHONE));

            items.add(new ContactModel(contactName, phoneNumber));
        }
        cursor.close();

        return items;
    }

    public int deleteEntry(String name) {
        // Define 'where' part of query.
        String selection = SQLContract.FeedEntry.COLUMN_NAME + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { name };
        // Issue SQL statement.

        int deletedRows = db.delete(SQLContract.FeedEntry.TABLE_NAME, selection, selectionArgs);

        return deletedRows;
    }

    public int updateEntry(String name, String newName, String newPhone) {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(SQLContract.FeedEntry.COLUMN_NAME, newName);
        values.put(SQLContract.FeedEntry.COLUMN_PHONE, newPhone);

        // Which row to update, based on the title
        String selection = SQLContract.FeedEntry.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = { name };

        int count = db.update(
                SQLContract.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }
}
