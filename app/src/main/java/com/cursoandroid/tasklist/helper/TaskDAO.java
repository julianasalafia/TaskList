package com.cursoandroid.tasklist.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cursoandroid.tasklist.model.Task;

import java.util.List;

public class TaskDAO implements ITaskDAO {
    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public TaskDAO(Context context) {
        DbHelper db = new DbHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public boolean save(Task task) {
        ContentValues cv = new ContentValues();
        cv.put("name", task.getTaskName());

        try {
            write.insert(DbHelper.TABLE_TASKS, null, cv);
            Log.i("INFO", "Saved task");
        } catch (Exception e) {
            Log.i("INFO", "Error saving the task" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Task task) {
        return false;
    }

    @Override
    public boolean delete(Task task) {
        return false;
    }

    @Override
    public List<Task> list() {
        return null;
    }
}
