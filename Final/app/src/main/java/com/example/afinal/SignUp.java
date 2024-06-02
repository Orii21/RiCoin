package com.example.afinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    EditText et_username, et_pass;
    Button btn_sign;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_username = findViewById(R.id.et_username_sign);
        et_pass = findViewById(R.id.et_pass_sign);
        btn_sign = findViewById(R.id.btn_SignUp);

        sharedPreferences = getSharedPreferences("akun", MODE_PRIVATE);

        btn_sign.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", et_username.getText().toString());
            editor.putString("password", et_pass.getText().toString());
            editor.apply();
            Toast.makeText(this, "Akun Berhasil dibuat", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });
    }
}