package com.example.appx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.appx.databases.TaskContract;
import com.example.appx.databases.TasksDBHelper;
import com.example.appx.entities.Task;
import com.example.appx.entities.TaskAdapter;

import java.util.ArrayList;

import static com.example.appx.RegistrationActivity.person;

public class MainScreenActivity extends AppCompatActivity {

    private TextView textViewPerson;
    private RecyclerView recyclerViewTasks;
    private TaskAdapter adapter;
    private String personalData;
    private TasksDBHelper dbHelper;
    private SQLiteDatabase database;
    private final ArrayList<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        textViewPerson = findViewById(R.id.textViewPerson);
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        dbHelper = new TasksDBHelper(this);
        database = dbHelper.getWritableDatabase();
        getData();

        if (person != null) {
            personalData = String.format("%s %s\nДолжность: %s", person.getName(), person.getSurname(), person.getPost());
            textViewPerson.setText(personalData);
        }


/*        //вставка в базу данных
        for (Task task : tasks) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(TaskContract.TasksEntry.ITEM_COLUMN_CONTENT, task.getContent());
            contentValues.put(TaskContract.TasksEntry.ITEM_COLUMN_STATUS, task.getStatus());
            contentValues.put(TaskContract.TasksEntry.ITEM_COLUMN_PERSON, task.getPerson());
            database.insert(TaskContract.TasksEntry.TABLE_NAME, null, contentValues);
        }*/


        adapter = new TaskAdapter(tasks);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTasks.setAdapter(adapter);

        //слушатели нажатий и свайпов
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
                remove(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewTasks);
    }

    //удаление задачи из базы
    private void remove(int position) {
        int id = tasks.get(position).getId();
        String where = TaskContract.TasksEntry._ID + " = ?";
        String[] whereArgs = new String[]{Integer.toString(id)};
        database.delete(TaskContract.TasksEntry.TABLE_NAME, where, whereArgs);
        getData();
        adapter.notifyDataSetChanged();
    }

    public void onClickToAddActivity(View view) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

    private void getData() {
        tasks.clear();
        Cursor cursor = database.query(TaskContract.TasksEntry.TABLE_NAME, null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(TaskContract.TasksEntry._ID));
            String content = cursor.getString(cursor.getColumnIndex(TaskContract.TasksEntry.ITEM_COLUMN_CONTENT));
            String status = cursor.getString(cursor.getColumnIndex(TaskContract.TasksEntry.ITEM_COLUMN_STATUS));
            String person = cursor.getString(cursor.getColumnIndex(TaskContract.TasksEntry.ITEM_COLUMN_PERSON));
            Task task = new Task(id, content, status, person);
            tasks.add(task);
        }
        cursor.close();
    }
}