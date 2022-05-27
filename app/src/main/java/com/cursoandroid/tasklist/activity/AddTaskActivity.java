package com.cursoandroid.tasklist.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

        editTask = findViewById(R.id.textTask);
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

                String taskName = editTask.getText().toString();

                if (!taskName.isEmpty()) {
                    Task task = new Task();
                    task.setTaskName(taskName);
                    taskDAO.save(task);
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
