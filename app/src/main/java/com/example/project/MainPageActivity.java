package com.example.project;

import android.os.Bundle;
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

public class MainPageActivity extends AppCompatActivity {
    RecyclerView mainRecyclerView ;
    List<TripeTypes> items ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Toast.makeText(this, "Second Done", Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_main_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        set();

        items =new ArrayList<>();
        items.add(new TripeTypes(R.drawable.adventure,R.drawable.adventrue_icon,"Adventure"));
        items.add(new TripeTypes(R.drawable.cultural,R.drawable.cultural_icon,"Cultural"));
        items.add(new TripeTypes(R.drawable.beach4,R.drawable.beach_icon,"Beach"));

        items.add(new TripeTypes(R.drawable.entertinment,R.drawable.entertainment_icon,"Entertainment"));



        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setAdapter(new MainPageAdapter(getApplicationContext(),items) );
        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
    }
    private void set(){
        mainRecyclerView=findViewById(R.id.recyclerViewMainPage);

    }
}
