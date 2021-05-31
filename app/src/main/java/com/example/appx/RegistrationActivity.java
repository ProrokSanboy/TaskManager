package com.example.appx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appx.entities.Person;

public class RegistrationActivity extends AppCompatActivity {

    public static Person person;

    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextPost;
    private EditText editTextLogin;
    private EditText editTextPassword;
    private EditText editTextPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
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
        if (!password.equals(password2)) {
            Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
            person = new Person(name, surname, post, login, password);
            Intent intent = new Intent(this, MainScreenActivity.class);
            startActivity(intent);
        }
    }
}