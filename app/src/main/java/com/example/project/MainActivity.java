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

public class  MainActivity extends AppCompatActivity {

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
        Button openSignButton = findViewById(R.id.sign_button);
        Button addCompanyButton = findViewById(R.id.addCompany_button);
        Button searchbtn = findViewById(R.id.searchAhmad);
        searchbtn.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, Search.class);
            startActivity(intent);
        });
        ahmad.setOnClickListener(v -> {
                    Intent intent = new Intent(MainActivity.this, AddTrip.class);
                    startActivity(intent);
              });
            Button qais = (Button) findViewById(R.id.qaisbtn);
            qais.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "FirstDone", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainPageActivity.class);

                    startActivity(intent);
                }
            });

            ahmad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, AddTrip.class);
                    startActivity(intent);
                }
                //

            });

<<<<<<< HEAD
            openSignButton.setOnClickListener(v -> {
=======
            openLoginButton.setOnClickListener(v -> {
>>>>>>> b5754ccfbf0ab3bf77ca419206c3e1adaaa01da7
                Intent intent = new Intent(MainActivity.this, SignInPage.class);
                startActivity(intent);
            });

            addCompanyButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, addCompany.class);
                startActivity(intent);
            });

        }

    }
