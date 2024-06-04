package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class AddTrip extends AppCompatActivity {
    private EditText titlefield, destfield, startPointfield, startDatefield,
            endDatefield, pricefield, descfield;
    private TextView risklvl;

    private Spinner typeSpnMenu, durSpnMenu;
    private ImageButton picturebtn, insertbtn;
    private CheckBox grillingbox, campingbox, swimbox, suitablebox, parkingbox;
    private SeekBar riskbar;

    private List<String> typeItems, durItems;

    private ActivityResultLauncher<Intent> resultLauncher;
    private ImageView testimage;
    private static final int PICK_IMAGE_REQUEST = 1;

    private String imageName;
    private StringBuilder boxesString;

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
        seekBarListner(riskbar, risklvl);
        registerResult();
        picturebtn.setOnClickListener(View -> pickImage());


    }

    private void pickImage() {
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }

    private void registerResult() {
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        try {
                            Uri imageUri = o.getData().getData();
                            testimage.setImageURI(imageUri);
                        } catch (Exception e) {
                            Toast.makeText(AddTrip.this, "Error", Toast.LENGTH_SHORT);
                        }
                    }
                });

    }

    private void populateTypeSpinner() {
        typeItems = new ArrayList<>();
        typeItems.add("Beach");
        typeItems.add("Adventure");
        typeItems.add("Cultural ");
        typeItems.add("Entertainment");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, typeItems);
        typeSpnMenu.setAdapter(adapter);

    }

    private void populateDurSpinner() {
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

    private void setupViews() {
        titlefield = findViewById(R.id.titleFld);
        destfield = findViewById(R.id.destinationFld);
        startPointfield = findViewById(R.id.startpointFld);
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
        riskbar = findViewById(R.id.riskBar);
        risklvl = findViewById(R.id.risklvl);
        testimage = findViewById(R.id.test);

    }

    private void seekBarListner(SeekBar bar, TextView label) {
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Convert progress to string and set it to the TextView
                String progressString = "Level: " + progress;
                label.setText(progressString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // You can add additional logic here if needed
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // You can add additional logic here if needed
            }
        });
    }

    private void getBoxes() {
        if (grillingbox.isSelected()) {
            boxesString.append(grillingbox.getText());
        } else if (campingbox.isSelected()) {
            boxesString.append(campingbox.getText());
        }
        else if (swimbox.isSelected()) {
            boxesString.append(swimbox.getText());
        }
        else if (suitablebox.isSelected()) {
            boxesString.append(suitablebox.getText());
        }else if (parkingbox.isSelected()) {
            boxesString.append(parkingbox.getText());
        }

    }
}
