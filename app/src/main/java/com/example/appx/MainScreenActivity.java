package com.example.appx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appx.entities.Task;
import com.example.appx.entities.TaskAdapter;
import com.example.appx.utils.DownloadTasksJSON;
import com.example.appx.utils.UrlBuilder;

import java.util.ArrayList;

public class MainScreenActivity extends AppCompatActivity {

    private TextView textViewPerson;
    private RecyclerView recyclerViewTasks;
    public static final ArrayList<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        textViewPerson = findViewById(R.id.textViewPerson);
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);


        /*DownloadTasksJSON downloadTasksJSON = new DownloadTasksJSON();
        downloadTasksJSON.execute(UrlBuilder.generateURL("/tasks").toString());*/

        TaskAdapter adapter = new TaskAdapter(tasks);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTasks.setAdapter(adapter);
        adapter.setOnTaskClickListener(new TaskAdapter.OnTaskClickListener() {
            @Override
            public void onTaskClick(int position) {
            }

            @Override
            public void onLongClick(int position) {
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                tasks.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewTasks);
    }

    public void onClickToAddActivity(View view) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }
}