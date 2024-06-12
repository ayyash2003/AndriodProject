package com.example.project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class details extends AppCompatActivity {

    private TextView companyNameTextView, companyEmailTextView, companyPhoneTextView, numberOfPassengersTextView, tripCostTextView;
    private ListView tripListView;
    private List<String> tripList = new ArrayList<>();
    private ArrayAdapter<String> tripListAdapter;

    private static final String COMPANY_DETAILS_URL = "http://192.168.1.244/Android/query.php";
    private static final String TRIP_LIST_URL = "http://192.168.1.244/Android/query.php";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        companyNameTextView = findViewById(R.id.company_name);
        companyEmailTextView = findViewById(R.id.company_email);
        companyPhoneTextView = findViewById(R.id.company_phone);
        numberOfPassengersTextView = findViewById(R.id.passenger_count);
        tripCostTextView = findViewById(R.id.trip_cost);

        tripListView = findViewById(R.id.trip_list_view);

        tripListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tripList);
        tripListView.setAdapter(tripListAdapter);

        loadCompanyDetails();
        loadTripList();
    }

    private void loadCompanyDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, COMPANY_DETAILS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseObject = new JSONObject(response);
                            JSONArray tripsArray = responseObject.getJSONArray("trips");

                            if (tripsArray.length() > 0) {
                                JSONObject companyObject = tripsArray.getJSONObject(0);  // Assuming all trips have the same company details
                                String name = companyObject.getString("company_name");
                                String email = companyObject.getString("company_email");
                                String phone = companyObject.getString("company_phone");
                                // int numberOfPassengers = companyObject.getInt("number_of_passengers");
                                double cost = companyObject.getDouble("trip_cost");

                                companyNameTextView.setText(name);
                                companyEmailTextView.setText(email);
                                companyPhoneTextView.setText(phone);
                                // numberOfPassengersTextView.setText("Number of Passengers: " );
                                tripCostTextView.setText("Cost: " + cost);

                                for (int i = 0; i < tripsArray.length(); i++) {
                                    JSONObject tripObject = tripsArray.getJSONObject(i);
                                    String tripTitle = tripObject.getString("trip_title");
                                    tripList.add(tripTitle);
                                }
                                tripListAdapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(details.this, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(details.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(details.this).add(stringRequest);
    }

    private void loadTripList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, TRIP_LIST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                // Check if the "title" key exists in the JSON object
                                if (object.has("title")) {
                                    String tripTitle = object.getString("title");
                                    tripList.add(tripTitle);
                                }
                            }
                            tripListAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(details.this, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(details.this, "Error loading trip list: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        // Add the request to the request queue
        Volley.newRequestQueue(details.this).add(stringRequest);
    }


}
