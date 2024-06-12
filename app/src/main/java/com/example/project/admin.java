package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class admin extends AppCompatActivity {

    private ImageView companyImageView;
    private TextView companyNameTextView;
    private ListView listView;
    private String[] listViewItems = {"Add Trip", "Details", "Link"};
    private String BASE_URL;
    private String companyLink;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        companyImageView = findViewById(R.id.imageA);
        companyNameTextView = findViewById(R.id.TextImage);
        listView = findViewById(R.id.listView);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listViewItems);
        listView.setAdapter(listViewAdapter);


        Intent intent = getIntent();
        String pass = intent.getStringExtra("pass");
        String email = intent.getStringExtra("email");



        BASE_URL = url(email, pass);


        loadCompanyData();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listViewItems[position].equals("Add Trip")) {
                    Intent intent = new Intent(admin.this, AddTrip.class);
                    intent.putExtra("companyname", companyNameTextView.getText());
                    startActivity(intent);
                } else if (listViewItems[position].equals("Details")) {
                    Intent intent = new Intent(admin.this, details.class);
                    startActivity(intent);
                } else if (listViewItems[position].equals("Link")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(companyLink));
                    startActivity(intent);
                }
            }
        });
    }

    private String url(String email, String pass) {
        return "http://172.19.49.100/Android/passEmail.php?email=" + email + "&pass=" + pass;
    }


    private void loadCompanyData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            if (array.length() > 0) {
                                JSONObject object = array.getJSONObject(0);
                                String name = object.getString("name");

                                companyNameTextView.setText(name);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(admin.this, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(admin.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(admin.this).add(stringRequest);
    }


}