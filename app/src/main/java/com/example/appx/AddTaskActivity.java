package com.example.appx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.appx.entities.Task;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextContent;
    private EditText editTextProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        editTextContent = findViewById(R.id.editTextContent);
        editTextProject = findViewById(R.id.editTextProject);
    }

    public void onClickAddTask(View view) {
        String content = editTextContent.getText().toString().trim();
        String project = editTextProject.getText().toString().trim();
        Task task = new Task(project, content);
        MainScreenActivity.tasks.add(task);
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
    }
}