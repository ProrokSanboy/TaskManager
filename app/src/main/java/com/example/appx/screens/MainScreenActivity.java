package com.example.appx.screens;

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

import com.example.appx.R;
import com.example.appx.databases.TaskContract;
import com.example.appx.databases.TasksDBHelper;
import com.example.appx.entities.Task;
import com.example.appx.entities.TaskAdapter;

import java.util.ArrayList;

import static com.example.appx.screens.RegistrationActivity.person;

public class MainScreenActivity extends AppCompatActivity {

    //элементы из макета
    private TextView textViewPerson;
    private RecyclerView recyclerViewTasks;
    private TaskAdapter adapter;
    private String personalData;

    //база данных
    private TasksDBHelper dbHelper;
    private SQLiteDatabase database;

    //массив задач
    private final ArrayList<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //соединение с макетом
        textViewPerson = findViewById(R.id.textViewPerson);
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);

        dbHelper = new TasksDBHelper(this);
        database = dbHelper.getWritableDatabase();

        //получение данных из базы данных (метод написан внизу)
        getData();

        if (person != null) {
            personalData = String.format("%s %s\nДолжность: %s", person.getName(), person.getSurname(), person.getPost());
            textViewPerson.setText(personalData);
        }

        //установка адаптера для наполнения RecyclerView задачами
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
                Intent intent = new Intent(getApplicationContext(), EditTaskActivity.class);
                intent.putExtra("id", tasks.get(position).getId());
                intent.putExtra("content", tasks.get(position).getContent());
                intent.putExtra("status", tasks.get(position).getStatus());
                intent.putExtra("person", tasks.get(position).getPerson());
                startActivity(intent);
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

    //нажатие на кнопку +
    public void onClickToAddActivity(View view) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

    //метод чтения данных из базы
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