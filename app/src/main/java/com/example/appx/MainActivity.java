package com.example.appx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextLogin;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    public void onClickEnter(View view) {
        String login = editTextLogin.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        Toast.makeText(this,  login + " " + password, Toast.LENGTH_SHORT).show();

    }

    public void onClickRegistration(View view) {
        String login = editTextLogin.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        Toast.makeText(this,  login + " " + password, Toast.LENGTH_SHORT).show();
    }
}