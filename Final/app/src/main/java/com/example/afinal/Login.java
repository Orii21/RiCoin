package com.example.afinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    TextView tv_login;
    EditText et_username,et_pass;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("akun", MODE_PRIVATE);


        tv_login = findViewById(R.id.tv_login);
        et_username = findViewById(R.id.et_username);
        et_pass = findViewById(R.id.et_pass);

        tv_login.setOnClickListener(v ->{
            String username = sharedPreferences.getString("username", "");
            String password = sharedPreferences.getString("password", "");
            if (!et_username.getText().toString().isEmpty() && !et_pass.getText().toString().isEmpty()) {
                if (et_username.getText().toString().equals(username) && et_pass.getText().toString().equals(password)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("is_logged_in", true);
                    editor.apply();
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }  else {
                    Toast.makeText(this, "username atau password salah. Silakan coba lagi.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Kolom tidak boleh kosong!!.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}