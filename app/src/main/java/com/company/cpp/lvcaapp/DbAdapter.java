package com.company.cpp.lvcaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAdapter {

    private static final String TAG = "DbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "adbname";
    private static final String SQLITE_TABLE = "atablename";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    public static final String[] bColumns = new String[] {
            "_id",
            "NAME",
            "SECOND_LINE",
            "HIDDEN",
    };

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "NAME TEXT, " +
                    "SECOND_LINE, " +
                    "HIDDEN" +
                    ");";

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }
    }

    public DbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public DbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }
    public Cursor fetchAll(String[] fields) {
        Cursor mCursor = mDb.query(SQLITE_TABLE, fields, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void insertSome() {
        AListItem.getInstance();
        String sampleData = "[{\"name\":\"Entry 1\",\"second_line\":\"Second Line 1\",\"hidden\":\"F\"},{\"name\":\"Entry 2\",\"second_line\":\"Second Line 2\",\"hidden\":\"F\"},{\"name\":\"Entry 3\",\"second_line\":\"Second Line 3\",\"hidden\":\"F\"},{\"name\":\"Entry 4\",\"second_line\":\"Second Line 4\",\"hidden\":\"F\"},{\"name\":\"EntryH 5\",\"second_line\":\"Second Line 5\",\"hidden\":\"T\"},{\"name\":\"Entry 6\",\"second_line\":\"Second Line 6\",\"hidden\":\"F\"},{\"name\":\"Entry 7\",\"second_line\":\"Second Line 7\",\"hidden\":\"F\"},{\"name\":\"Entry 8\",\"second_line\":\"Second Line 8\",\"hidden\":\"F\"},{\"name\":\"Entry 9\",\"second_line\":\"Second Line 9\",\"hidden\":\"F\"},{\"name\":\"EntryH 10\",\"second_line\":\"Second Line 10\",\"hidden\":\"T\"},{\"name\":\"Entry 11\",\"second_line\":\"Second Line 11\",\"hidden\":\"F\"},{\"name\":\"Entry 12\",\"second_line\":\"Second Line 12\",\"hidden\":\"F\"},{\"name\":\"Entry 13\",\"second_line\":\"Second Line 13\",\"hidden\":\"F\"},{\"name\":\"Entry 14\",\"second_line\":\"Second Line 14\",\"hidden\":\"F\"},{\"name\":\"EntryH 15\",\"second_line\":\"Second Line 15\",\"hidden\":\"T\"},{\"name\":\"Entry 16\",\"second_line\":\"Second Line 16\",\"hidden\":\"F\"},{\"name\":\"Entry 17\",\"second_line\":\"Second Line 17\",\"hidden\":\"F\"},{\"name\":\"Entry 18\",\"second_line\":\"Second Line 18\",\"hidden\":\"F\"},{\"name\":\"Entry 19\",\"second_line\":\"Second Line 19\",\"hidden\":\"F\"},{\"name\":\"EntryH 20\",\"second_line\":\"Second Line 20\",\"hidden\":\"T\"},{\"name\":\"Entry 21\",\"second_line\":\"Second Line 21\",\"hidden\":\"F\"},{\"name\":\"Entry 22\",\"second_line\":\"Second Line 22\",\"hidden\":\"F\"},{\"name\":\"Entry 23\",\"second_line\":\"Second Line 23\",\"hidden\":\"F\"}]";
        String[] items = sampleData.split("\\},\\{");
        for(String item: items){
            AListItem.clear();
            AListItem.load(item);
            if(AListItem.getName().contains("Hide")){
                AListItem.setHidden("T");
            }

            mDb.insert(SQLITE_TABLE, null, AListItem.getContentValues());

            ContentValues values = AListItem.getContentValues();
            Log.v(TAG, "values.toString()" + values.toString());
        }
    }

    public boolean deleteAll() {
        int doneDelete = 0;
        doneDelete = mDb.delete(SQLITE_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }
}









































