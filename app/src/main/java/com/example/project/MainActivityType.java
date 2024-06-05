package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivityType extends AppCompatActivity {

    private List<Trips> items = new ArrayList<>();

    private RequestQueue queue ;
    RecyclerView recyclerViewType ;
    private String BASE_URL ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_type);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_type), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        set();
        recyclerViewType.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        BASE_URL=url(type);
        Toast.makeText(MainActivityType.this,type,Toast.LENGTH_SHORT).show();
  //      loadItems();


    }
    private void set(){
        recyclerViewType=findViewById(R.id.recyclerViewType);
    }
    private String url(String type){
        return "http://192.168.1.248/Android/type.php?type="+type ;
    }

    private void loadItems(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);

                                String title = object.getString("title");
                                String type = object.getString("type");
                                String destination = object.getString("destination");
                                String image = object.getString("image");
                                String startingpoint = object.getString("startingpoint");
                                String duration = object.getString("duration");
                                String startdate = object.getString("startdate");
                                String enddate = object.getString("enddate");
                                double price = object.getDouble("price");
                                String risk = object.getString("risk");
                                String description = object.getString("description");
                                String checkbox	 = object.getString("checkbox");



                                Trips trips = new Trips(title,type,destination, image,startingpoint,duration,startdate,enddate,price,risk,description,checkbox);
                                items.add(trips);
                            }

                        }catch (Exception e){

                        }

                        TypeAdapter adapter = new TypeAdapter(getApplicationContext(), items);
                        recyclerViewType.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(MainActivityType.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(MainActivityType.this).add(stringRequest);


    }




//    @Override
//    public void onTypeClick(Trips item) {
//
//    }
}
