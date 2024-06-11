package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    private CheckBox grillingbox,campingbox,swimbox,parkingbox,suitablebox;
    private MaterialToolbar toolbar;
    private ChipGroup chipGroup;
    private RadioGroup radioGroup;
    private Button search;
    private StringBuilder selectedchips = new StringBuilder();
    private StringBuilder boxesString = new StringBuilder();
    private String destination,boxes,type,url;
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

         setupViews();
        setSupportActionBar(toolbar);


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

        for (String s: arraylist){
            Chip chip =(Chip) LayoutInflater.from(Search.this).inflate(R.layout.chip_layout, null);
            chip.setText(s);
            chip.setId(View.generateViewId());
            chipGroup.addView(chip);
        }
        chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                if(checkedIds.isEmpty()){
                }
                else {
                        for(int id: checkedIds){
                            Chip chip = findViewById(id);
                            selectedchips.append(",").append(chip.getText());
                        }
                }

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                if (radioButton != null) {
                    type = radioButton.getText().toString();
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination = selectedchips.toString().trim();
                boxes = boxesString.toString().trim();
                url =  "http://192.168.1.103/Android.search.php?type="+type+"&destination="+destination+"&checkbox="+boxes;
                Intent intent = new Intent(Search.this, MainActivityType.class);
                intent.putExtra("url",url);
            }
        });

    }
    public void setupViews(){
        toolbar = findViewById(R.id.toolbar);
        chipGroup = findViewById(R.id.chipGroup);
        radioGroup = findViewById(R.id.radioGroup);
        grillingbox = findViewById(R.id.checkboxGrilling);
        campingbox = findViewById(R.id.checkboxCamping);
        swimbox = findViewById(R.id.checkboxSwimming);
        suitablebox = findViewById(R.id.checkboxSuitableForChildren);
        parkingbox = findViewById(R.id.checkboxParking);
        search = findViewById(R.id.submitButton);
    }
    private void getBoxes() {
        if (grillingbox.isChecked()) {
            boxesString.append(grillingbox.getText()).append(", ");
        }  if (campingbox.isChecked()) {
            boxesString.append(campingbox.getText()).append(", ");
        }
        if (swimbox.isChecked()) {
            boxesString.append(swimbox.getText()).append(", ");
        }
        if (suitablebox.isChecked()) {
            boxesString.append(suitablebox.getText()).append(", ");
        } if (parkingbox.isChecked()) {
            boxesString.append(parkingbox.getText()).append("\n");
        }

    }

}