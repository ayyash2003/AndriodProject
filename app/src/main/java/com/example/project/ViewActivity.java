package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

public class ViewActivity extends AppCompatActivity {
    private int id ;
    private RequestQueue queue ;
    private String BASE_URL ;
    private ImageView imgView ;
    private TextView txtTripNameViewPage ;
    private TextView txtDestination;
    private TextView txtStartPoint;
    private TextView txtDate ;

    private TextView txtDescription;
    private TextView txtPrice ;
    private CheckBox checkboxGrilling,checkboxParking,checkboxSwimming,checkboxCamping,checkboxSuitableForChildren;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view);
        set();
        Intent intent = getIntent();

        id =intent.getIntExtra("id",0);
        BASE_URL=url(id+"");
        loadItems();

    }
    private String url(String id){
        return "http://192.168.1.103/Android/view.php?id="+id ;
    }
    private void set(){
        imgView=(ImageView) findViewById(R.id.imgView);
        txtTripNameViewPage=findViewById(R.id.txtTripNameViewPage);
        txtDestination=findViewById(R.id.txtDestination);
        txtStartPoint=findViewById(R.id.txtStartPoint);
        txtDescription=findViewById(R.id.txtDescription);
        txtPrice=findViewById(R.id.txtPrice);
        txtDate=findViewById(R.id.txtDate);
        checkboxGrilling=findViewById(R.id.checkboxGrilling);
        checkboxParking=findViewById(R.id.checkboxParking);
        checkboxSwimming=findViewById(R.id.checkboxSwimming);
        checkboxCamping=findViewById(R.id.checkboxCamping);
        checkboxSuitableForChildren=findViewById(R.id.checkboxSuitableForChildren);



    }


    private void loadItems(){
        Toast.makeText(ViewActivity.this,"First",Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {

                            JSONArray array = new JSONArray(response);


                                JSONObject object = array.getJSONObject(0);
                                int id =object.getInt("id");
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
                                Glide.with(ViewActivity.this).load(image).into(imgView);
                                txtTripNameViewPage.setText(title);
                                txtDestination.setText(destination);
                                txtStartPoint.setText(startingpoint);
                                txtDate.setText(startdate);
                                txtDescription.setText(description);
                                txtPrice.setText(price+"");
                                checkboxes(checkbox);


                        }catch (Exception e){

                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(ViewActivity.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(ViewActivity.this).add(stringRequest);


    }
    private void checkboxes(String box){

        if(box.contains("Grilling")){
            checkboxGrilling.setChecked(true);
        }
//        else
//            checkboxGrilling.setVisibility(View.GONE);

        if(box.contains("Swimming")){
            checkboxSwimming.setChecked(true);
        }
//        else
//            checkboxSwimming.setVisibility(View.GONE);
        if(box.contains("Parking"))
            checkboxParking.setChecked(true);
//        else
//            checkboxParking.setVisibility(View.GONE);
        if(box.contains("Camping")){
            checkboxCamping.setChecked(true);
        }
//        else
//            checkboxCamping.setVisibility(View.GONE);

        if(box.contains("Suitable for Children")){
            checkboxSuitableForChildren.setChecked(true);
        }
//        else
//            checkboxSuitableForChildren.setVisibility(View.GONE);

        //checkboxGrilling.setChecked(true);
        checkboxGrilling.setClickable(false);
        checkboxSwimming.setClickable(false);
        checkboxParking.setClickable(false);
        checkboxCamping.setClickable(false);
        checkboxSuitableForChildren.setClickable(false);

    }

    public void onHomePageClick(View view){
        Intent intent =new Intent(this,MainPageActivity.class);
        startActivity(intent);
    }
    public void onReservedClick(View view){

    }

}