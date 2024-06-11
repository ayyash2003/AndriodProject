package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainPageActivity extends AppCompatActivity implements SelectMainPage {
    RecyclerView mainRecyclerView ;
    List<TripTypes> items ;
    TextView txtLogin ;
    Spinner spnLogin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        set();

        items =new ArrayList<>();
        items.add(new TripTypes(R.drawable.adventure,R.drawable.adventrue_icon,"Adventure"));
        items.add(new TripTypes(R.drawable.cultural,R.drawable.cultural_icon,"Cultural"));
        items.add(new TripTypes(R.drawable.beach4,R.drawable.beach_icon,"Beach"));

        items.add(new TripTypes(R.drawable.entertinment,R.drawable.entertainment_icon,"Entertainment"));

        intent();

        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setAdapter(new MainPageAdapter(getApplicationContext(),items,this) );
//        spnLogin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                OnSpinnerClick(position);
//            }
//        });
        spnLogin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle item selection
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainPageActivity.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
                                OnSpinnerClick(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }
    private void intent(){
        Intent intent =getIntent();
        String name =intent.getStringExtra("name");
        if(name!=null) {
            List<String> typeItems = new ArrayList<>();
            typeItems.add(name);
            typeItems.add("Log out");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, typeItems);
            spnLogin.setAdapter(adapter);
            spnLogin.setSelection(0);
            spnLogin.setVisibility(View.VISIBLE);
            txtLogin.setVisibility(View.GONE);


        }
    }
    public void OnSpinnerClick(int position){
        if (position ==1){
            spnLogin.setVisibility(View.GONE);
            txtLogin.setVisibility(View.VISIBLE);
        }
    }

    private void set(){
        txtLogin=findViewById(R.id.txtLogin);
        spnLogin=findViewById(R.id.spnLogin);
        mainRecyclerView=findViewById(R.id.recyclerViewMainPage);

    }

    @Override
    public void onItemClick(TripTypes item) {
        Intent intent =new Intent(this,MainActivityType.class);
        intent.putExtra("type",item.getType());
        startActivity(intent);

    }

    public void onLoginClick(View view){
        Intent intent =new Intent(MainPageActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    public void onSearchClick(View view ){
        Intent intent =new Intent(this,Search.class);
        startActivity(intent);
    }


}
