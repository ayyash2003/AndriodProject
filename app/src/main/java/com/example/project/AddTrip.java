package com.example.project;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class AddTrip extends AppCompatActivity {
    EditText titlefield, destfield, startPointfield, endPointfield, startDatefield,
            endDatefield, pricefield, descfield;
    Spinner typeSpnMenu, durSpnMenu, descSpnMenu;
    ImageButton picturebtn, insertbtn;
    CheckBox grillingbox,campingbox,swimbox,suitablebox,parkingbox;
     List<String> typeItems,durItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_trip);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setupViews();
        populateTypeSpinner();
        populateDurSpinner();
    }

    public void populateTypeSpinner(){
    typeItems = new ArrayList<>();
    typeItems.add("Adventure");
    typeItems.add("Cultural ");
    typeItems.add("Relaxation and Wellness");
    typeItems.add("Food and Gourmet");

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, typeItems);
        typeSpnMenu.setAdapter(adapter);

    }
    public void populateDurSpinner(){
        durItems = new ArrayList<>();
        durItems.add("1 Day");
        durItems.add("2 Days");
        durItems.add("3 Days");
        durItems.add("4 Days");
        durItems.add("5 Days");
        durItems.add("6 Days");
        durItems.add("7 Days");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, durItems);
        durSpnMenu.setAdapter(adapter);

    }
    public void populateDescSpinner(){

    }
    private void setupViews() {
        titlefield = findViewById(R.id.titleFld);
        destfield = findViewById(R.id.destinationFld);
        startPointfield = findViewById(R.id.startpointFld);
        endPointfield = findViewById(R.id.endpointFld);
        startDatefield = findViewById(R.id.startdateFld);
        endDatefield = findViewById(R.id.enddateFld);
        pricefield = findViewById(R.id.priceFld);
        descfield = findViewById(R.id.descriptionFld);
        typeSpnMenu = findViewById(R.id.typeSpin);
        durSpnMenu = findViewById(R.id.durationSpin);
        picturebtn = findViewById(R.id.picturebtn);
        grillingbox = findViewById(R.id.grillingBox);
        campingbox = findViewById(R.id.campingBox);
        swimbox = findViewById(R.id.swimBox);
        suitablebox = findViewById(R.id.suitableBox);
        parkingbox = findViewById(R.id.parkingBox);
        insertbtn = findViewById(R.id.insertbtn);
    }
}