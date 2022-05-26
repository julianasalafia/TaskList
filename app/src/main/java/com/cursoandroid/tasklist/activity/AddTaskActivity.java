package com.cursoandroid.tasklist.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cursoandroid.tasklist.R;
import com.cursoandroid.tasklist.helper.TaskDAO;
import com.cursoandroid.tasklist.model.Task;
import com.google.android.material.textfield.TextInputEditText;

public class AddTaskActivity extends AppCompatActivity {
    private TextInputEditText editTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSave:
                TaskDAO taskDAO = new TaskDAO(getApplicationContext());

                Task task = new Task();
                task.setTaskName("Go shopping");
                taskDAO.save(task);
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
