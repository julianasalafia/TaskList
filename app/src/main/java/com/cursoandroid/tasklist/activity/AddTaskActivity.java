package com.cursoandroid.tasklist.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.tasklist.R;
import com.cursoandroid.tasklist.helper.TaskDAO;
import com.cursoandroid.tasklist.model.Task;
import com.google.android.material.textfield.TextInputEditText;

public class AddTaskActivity extends AppCompatActivity {
    private TextInputEditText editTask;
    private Task currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTask = findViewById(R.id.textTask);
        currentTask = (Task) getIntent().getSerializableExtra("selectedTask");

        if (currentTask != null) {
            editTask.setText(currentTask.getTaskName());
        }
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

                if (currentTask != null) {
                    String taskName = editTask.getText().toString();
                    if (!taskName.isEmpty()) {
                        Task task = new Task();
                        task.setTaskName(taskName);
                        task.setId(currentTask.getId());

                        if (taskDAO.update(task)) {
                            finish();
                            Toast.makeText(getApplicationContext(),
                                    "Task updated",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Task not updated",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    String taskName = editTask.getText().toString();

                    if (!taskName.isEmpty()) {
                        Task task = new Task();
                        task.setTaskName(taskName);
                        if (taskDAO.save(task)) {
                            finish();
                            Toast.makeText(getApplicationContext(),
                                    "Task saved",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Task not saved",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
