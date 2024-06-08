package com.example.project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class admin extends AppCompatActivity {

    private List<Company> items = new ArrayList<>();
    private RecyclerView recycler;
    private ListView listView;
    private String[] listViewItems = {"Add Trip", "Details", "Link"};
    private static final String BASE_URL = "http://10.0.2.2:84/rest2/get_items.php";

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.company_recycler);
        listView = findViewById(R.id.listView);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        loadItems();


        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listViewItems);
        listView.setAdapter(listViewAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listViewItems[position].equals("Add Trip")) {
                    Intent intent = new Intent(admin.this, AddTrip.class);
                    startActivity(intent);
                } else if (listViewItems[position].equals("Details")) {
                    Intent intent = new Intent(admin.this, details.class);
                    startActivity(intent);
                } else if (listViewItems[position].equals("Link")) {
                    Intent intent = new Intent(admin.this, AddTrip.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void loadItems() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                String name = object.getString("name");
                                String image = object.getString("image");

                                Company company = new Company(name, image);
                                items.add(company);
                            }

                            ImagesAdapter adapter = new ImagesAdapter(admin.this, items);
                            recycler.setAdapter(adapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(admin.this, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(admin.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(admin.this).add(stringRequest);
    }
}
