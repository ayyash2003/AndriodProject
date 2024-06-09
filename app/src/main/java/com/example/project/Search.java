package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ChipGroup chipGroup = findViewById(R.id.chipGroup);
        ArrayList<String> arraylist = new ArrayList<>();
        arraylist.add("Ramallah");
        arraylist.add("Tulkarm");
        arraylist.add("Hebron");
        arraylist.add("Jenin");
        arraylist.add("Nablus");
        arraylist.add("Tubas");
        arraylist.add("Salfit");
        arraylist.add("Jerusalem");
        arraylist.add("Bethlehem");
        Random random = new Random();
        for (String s: arraylist){
            Chip chip =(Chip) LayoutInflater.from(Search.this).inflate(R.layout.chip_layout, null);
            chip.setText(s);
            chip.setId(random.nextInt());
            chipGroup.addView(chip);
        }
        chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                if(checkedIds.isEmpty()){

                }
                else {
                    StringBuilder stringBuilder = new StringBuilder();
                        for(int i: checkedIds){
                            Chip chip = findViewById(i);
                            stringBuilder.append(", ").append(chip.getText());
                        }
                }

            }
        });


    }
}