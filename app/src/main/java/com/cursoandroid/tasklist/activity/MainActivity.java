package com.cursoandroid.tasklist.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.tasklist.R;
import com.cursoandroid.tasklist.adapter.TaskAdapter;
import com.cursoandroid.tasklist.helper.RecyclerItemClickListener;
import com.cursoandroid.tasklist.helper.TaskDAO;
import com.cursoandroid.tasklist.model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList = new ArrayList<>();
    private Task selectedTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Task selectedTask = taskList.get(position);

                                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                                intent.putExtra("selectedTask", selectedTask);

                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                selectedTask = taskList.get(position);

                                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                                dialog.setTitle("Confirm deletion");
                                dialog.setMessage("Do you want to delete the task " + selectedTask.getTaskName() + "?");

                                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        TaskDAO taskDAO = new TaskDAO(getApplicationContext());
                                        if (taskDAO.delete(selectedTask)) {
                                            loadTaskList();
                                            Toast.makeText(getApplicationContext(),
                                                    "Task deleted successfully",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(),
                                                    "Error while excluding task",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                                dialog.setNegativeButton("No", null);

                                dialog.create();
                                dialog.show();

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddTaskActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loadTaskList() {
        TaskDAO taskDAO = new TaskDAO(getApplicationContext());
        taskList = taskDAO.list();

        taskAdapter = new TaskAdapter(taskList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(taskAdapter);
    }

    @Override
    protected void onStart() {
        loadTaskList();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
