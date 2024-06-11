package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

<<<<<<< HEAD
        Button ahmadButton = findViewById(R.id.ahmadbtn);
        Button openSignButton = findViewById(R.id.sign_button);
=======

        Button ahmad = findViewById(R.id.ahmadbtn);
        Button openLoginButton = findViewById(R.id.login_button);
>>>>>>> 1360e89e427f7e11f9217899928763ab44c63caa
        Button addCompanyButton = findViewById(R.id.addCompany_button);
        Button searchButton = findViewById(R.id.searchAhmad);
        Button qaisButton = findViewById(R.id.qaisbtn);

        searchButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Search.class);
            startActivity(intent);
        });

        ahmadButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTrip.class);
            startActivity(intent);
        });

        qaisButton.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "FirstDone", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MainPageActivity.class);
            startActivity(intent);
        });

<<<<<<< HEAD
        openSignButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignInPage.class);
            startActivity(intent);
        });
=======
            });

            openLoginButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, SignInPage.class);
                startActivity(intent);
            });

            addCompanyButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, addCompany.class);
                startActivity(intent);
            });

        }
>>>>>>> 1360e89e427f7e11f9217899928763ab44c63caa

        addCompanyButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, addCompany.class);
            startActivity(intent);
        });
    }
}
