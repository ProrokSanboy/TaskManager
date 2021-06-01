package com.example.appx.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appx.R;
import com.example.appx.databases.PersonContract;
import com.example.appx.databases.PersonDBHelper;
import com.example.appx.entities.Person;

import static com.example.appx.screens.RegistrationActivity.person;

public class EnterActivity extends AppCompatActivity {
    private EditText editTextLogin;
    private EditText editTextPassword;

    private PersonDBHelper dbHelper;
    private SQLiteDatabase database;

    private String login;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new PersonDBHelper(this);
        database = dbHelper.getWritableDatabase();
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    public void onClickEnter(View view) {
        login = editTextLogin.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        if (!login.isEmpty() && !password.isEmpty()) {
            getPersonalData();
            if (person != null) {
                editTextLogin.setText("");
                editTextPassword.setText("");
                Intent intent = new Intent(this, MainScreenActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Неверные логин и пароль", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,  "Введите логин и пароль" , Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickRegistration(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    private void getPersonalData() {
        String selection = PersonContract.PersonEntry.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = new String[]{password};
        Cursor cursor = database.query(PersonContract.PersonEntry.TABLE_NAME, null, selection,selectionArgs,null,null,null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(PersonContract.PersonEntry.COLUMN_NAME));
            String surname = cursor.getString(cursor.getColumnIndex(PersonContract.PersonEntry.COLUMN_SURNAME));
            String post = cursor.getString(cursor.getColumnIndex(PersonContract.PersonEntry.COLUMN_POST));
            person = new Person(name, surname, post);
        }
    }
}