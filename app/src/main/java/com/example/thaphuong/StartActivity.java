package com.example.thaphuong;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.thaphuong.Admin.LoginActivity;

import java.util.Date;

public class StartActivity extends AppCompatActivity {

    Button  buttonStart, buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);
        buttonStart = findViewById(R.id.buttonStart);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonStart.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
        });
        buttonLogin.setOnClickListener(v -> {
                        Intent intent = new Intent(StartActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}