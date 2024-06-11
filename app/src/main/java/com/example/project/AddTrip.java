package com.example.project;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddTrip extends AppCompatActivity {
    private EditText titlefield, destfield, startPointfield, pricefield, descfield;
    private TextView risklvl;
    private DatePicker startDatefield, endDatefield;

    private Spinner typeSpnMenu, durSpnMenu;
    private ImageButton picturebtn, insertbtn;
    private CheckBox grillingbox, campingbox, swimbox, suitablebox, parkingbox;
    private SeekBar riskbar;

    private List<String> typeItems, durItems;


    private ImageView testimage;

    Bitmap bitmap;
    Intent intent ;
    String comapnyName ;
    private final StringBuilder boxesString = new StringBuilder();

    public AddTrip() {
    }


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
        intent =getIntent();
        comapnyName=intent.getStringExtra("companyname");
        setupViews();
        populateTypeSpinner();
        populateDurSpinner();
        seekBarListner(riskbar, risklvl);

        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getBoxes();
                insertData();

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
        startDatefield = findViewById(R.id.startDatePicker);
        endDatefield = findViewById(R.id.endDatePicker);
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
    private void insertData() {

        String title = titlefield.getText().toString().trim();
        String type = typeSpnMenu.getSelectedItem().toString();
        String destination = destfield.getText().toString().trim();
        String image = "image_placeholder"; // Replace this with actual image handling code
        String startingpoint = startPointfield.getText().toString().trim();
        String duration = durSpnMenu.getSelectedItem().toString();
        int startYear = startDatefield.getYear();
        int startMonth = startDatefield.getMonth() + 1; // Month is 0-based, so add 1
        int startDay = startDatefield.getDayOfMonth();

        int endYear = endDatefield.getYear();
        int endMonth = endDatefield.getMonth() + 1; // Month is 0-based, so add 1
        int endDay = endDatefield.getDayOfMonth();

        // Format the dates as "yyyy-MM-dd"
        String startdate = String.format("%04d-%02d-%02d", startYear, startMonth, startDay);
        String enddate = String.format("%04d-%02d-%02d", endYear, endMonth, endDay);
        String price = pricefield.getText().toString().trim();
        String risk = (String) risklvl.getText();
        String description = descfield.getText().toString().trim();
        String checkbox = boxesString.toString();


        Log.e("TAG", "boxes is: " + checkbox);

        String url = "http://192.168.1.103/Android/insert_trip.php";
        RequestQueue queue = Volley.newRequestQueue(AddTrip.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    String msg = jsonObject.getString("message");
                    Toast.makeText(AddTrip.this,msg
                            , Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(AddTrip.this,
                        "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our
                // key and value pair to our parameters.
                params.put("title", title);
                params.put("type", type);
                params.put("destination", destination);
                params.put("image", image);
                params.put("startingpoint", startingpoint);
                params.put("duration", duration);
                params.put("startdate", startdate);
                params.put("enddate", enddate);
                params.put("price", price);
                params.put("risk", risk);
                params.put("description", description);
                params.put("checkbox", checkbox);
               // params.put("companyname",comapnyName);
                return params;

            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }

}
