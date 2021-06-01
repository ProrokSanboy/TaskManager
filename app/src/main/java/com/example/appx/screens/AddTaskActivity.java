package com.example.appx.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appx.R;
import com.example.appx.databases.TaskContract;
import com.example.appx.databases.TasksDBHelper;
import com.example.appx.entities.Task;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextContent;
    private Spinner spinnerPersons;
    private RadioGroup radioGroupStatus;

    private TasksDBHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        dbHelper = new TasksDBHelper(this);
        database = dbHelper.getWritableDatabase();

        editTextContent = findViewById(R.id.editTextContent);
        spinnerPersons = findViewById(R.id.spinnerPersons);
        radioGroupStatus = findViewById(R.id.radioGroup);
    }

    public void onClickAddTask(View view) {
        String content = editTextContent.getText().toString().trim();
        String person = spinnerPersons.getSelectedItem().toString();
        int radioButtonId = radioGroupStatus.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        String status = radioButton.getText().toString();
        Task task = new Task(content, status, person);
        if (!content.isEmpty()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(TaskContract.TasksEntry.ITEM_COLUMN_CONTENT, task.getContent());
            contentValues.put(TaskContract.TasksEntry.ITEM_COLUMN_STATUS, task.getStatus());
            contentValues.put(TaskContract.TasksEntry.ITEM_COLUMN_PERSON, task.getPerson());
            database.insert(TaskContract.TasksEntry.TABLE_NAME, null, contentValues);
            Intent intent = new Intent(this, MainScreenActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Введите текст задачи", Toast.LENGTH_SHORT).show();
        }
    }

}