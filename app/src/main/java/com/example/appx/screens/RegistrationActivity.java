package com.example.appx.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appx.R;
import com.example.appx.databases.PersonContract;
import com.example.appx.databases.PersonDBHelper;
import com.example.appx.entities.Person;
import com.example.appx.screens.MainScreenActivity;

public class RegistrationActivity extends AppCompatActivity {

    public static Person person;

    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextPost;
    private EditText editTextLogin;
    private EditText editTextPassword;
    private EditText editTextPassword2;

    private PersonDBHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        dbHelper = new PersonDBHelper(this);
        database = dbHelper.getWritableDatabase();
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextPost = findViewById(R.id.editTextPost);
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPassword2 = findViewById(R.id.editTextPassword2);
    }

    public void onClickAddPerson(View view) {
        String name = editTextName.getText().toString().trim();
        String surname = editTextSurname.getText().toString().trim();
        String post = editTextPost.getText().toString().trim();
        String login = editTextLogin.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String password2 = editTextPassword2.getText().toString().trim();


        if (!name.isEmpty() && !surname.isEmpty() && !post.isEmpty() && !login.isEmpty() && !password.isEmpty() && !password2.isEmpty()) {
            if (!password.equals(password2)) {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
            } else {
                person = new Person(name, surname, post, login, password);
                ContentValues cv = new ContentValues();
                cv.put(PersonContract.PersonEntry.COLUMN_NAME, name);
                cv.put(PersonContract.PersonEntry.COLUMN_SURNAME, surname);
                cv.put(PersonContract.PersonEntry.COLUMN_POST, post);
                cv.put(PersonContract.PersonEntry.COLUMN_LOGIN, login);
                cv.put(PersonContract.PersonEntry.COLUMN_PASSWORD, password);
                database.insert(PersonContract.PersonEntry.TABLE_NAME, null, cv);
                Toast.makeText(this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainScreenActivity.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
        }
    }
}