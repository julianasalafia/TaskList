package com.cursoandroid.tasklist.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
    public static int VERSION = 2;
    public static String NAME_DB = "DB_TASK";
    public static String TABLE_TASKS = "tasks";


    public DbHelper(Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " name TEXT NOT NULL)";

        try {
            db.execSQL(sql);
            Log.i("INFO DB", "Table created with success");
        } catch (Exception e) {
            Log.i("INFO DB", "Error while creating table" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_TASKS + " ;";

        try {
            db.execSQL(sql);
            onCreate(db);
            Log.i("INFO DB", "Updated with success");
        } catch (Exception e) {
            Log.i("INFO DB", "Error updating" + e.getMessage());
        }
    }
}
