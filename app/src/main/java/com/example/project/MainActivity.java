package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button ahmad = findViewById(R.id.ahmadbtn);
        Button openLoginButton = findViewById(R.id.login_button);
        Button addCompanyButton = findViewById(R.id.addCompany_button);
        ahmad.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTrip.class);
            startActivity(intent);
        });

        openLoginButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginPage.class);
            startActivity(intent);
        });

        addCompanyButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, addCompany.class);
            startActivity(intent);
        });

    }
}
