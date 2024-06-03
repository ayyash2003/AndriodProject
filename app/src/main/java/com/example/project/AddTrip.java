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
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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
    EditText titlefield, destfield, startPointfield, startDatefield,
            endDatefield, pricefield, descfield;
    TextView risklvl;

    Spinner typeSpnMenu, durSpnMenu;
    ImageButton picturebtn, insertbtn;
    CheckBox grillingbox,campingbox,swimbox,suitablebox,parkingbox;
    SeekBar riskbar;
     List<String> typeItems,durItems;

    private static final int PICK_IMAGE_REQUEST = 1;

    private String imageName;
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

        picturebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            String imageName = getImageName(imageUri);
            Toast.makeText(this, "Selected Image: " + imageName, Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("Range")
    private String getImageName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            // If fetching image name fails, use the last segment of the URI
            result = uri.getLastPathSegment();
        }
        return result;
    }






    


    public void populateTypeSpinner(){
    typeItems = new ArrayList<>();
    typeItems.add("Beach");
    typeItems.add("Adventure");
    typeItems.add("Cultural ");
    typeItems.add("Entertainment");


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

    }
    public void seekBarListner(SeekBar bar, TextView label){
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

    }
