package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
    RecyclerView mainRecyclerView;
    List<TripTypes> items;
    TextView txtLogin;
    Spinner spnLogin;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefrs";
    public static final String EMPTY = "empty";


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
        // userName();
        new LoadDataAsyncTask().execute();
        items = new ArrayList<>();
        items.add(new TripTypes(R.drawable.adventure, R.drawable.adventrue_icon, "Adventure"));
        items.add(new TripTypes(R.drawable.cultural, R.drawable.cultural_icon, "Cultural"));
        items.add(new TripTypes(R.drawable.beach4, R.drawable.beach_icon, "Beach"));

        items.add(new TripTypes(R.drawable.entertinment, R.drawable.entertainment_icon, "Entertainment"));

        intent();

        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setAdapter(new MainPageAdapter(getApplicationContext(), items, this));

        spnLogin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle item selection
                String selectedItem = parent.getItemAtPosition(position).toString();
                OnSpinnerClick(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    public void userName() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        int empty = sharedpreferences.getInt("empty", -1);
        // int empty=
        if (empty != -1) {
            int id = sharedpreferences.getInt("id", -1);
            String name = sharedpreferences.getString("name", "");
            String email = sharedpreferences.getString("email", "");
            String pass = sharedpreferences.getString("pass", "");
            String gender = sharedpreferences.getString("gender", "");
            String city = sharedpreferences.getString("city", "");
            Data.UserID = empty;
            Data.user = new User(id, name, email, pass, gender, city);
            txtLogin.setVisibility(View.GONE);
            spinner(name);
            spnLogin.setVisibility(View.VISIBLE);


        }

    }

    private String url(String type) {
        return "http://172.19.49.100/Android/type.php?type=" + type;
    }


    private void intent() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if (name != null) {
            Toast.makeText(this, "intent", Toast.LENGTH_SHORT).show();
            spinner(name);
            spnLogin.setVisibility(View.VISIBLE);
            txtLogin.setVisibility(View.GONE);


        }
    }

    public void spinner(String name) {
        List<String> typeItems = new ArrayList<>();
        typeItems.add(name);
        typeItems.add("Log out");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, typeItems);
        spnLogin.setAdapter(adapter);
        spnLogin.setSelection(0);
    }

    public void OnSpinnerClick(int position) {
        if (position == 1) {
            spnLogin.setVisibility(View.GONE);
            txtLogin.setVisibility(View.VISIBLE);
            Data.UserID = -1;
            Data.user = null;
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.apply();

        }
    }

    private void set() {
        txtLogin = findViewById(R.id.txtLogin);
        spnLogin = findViewById(R.id.spnLogin);
        mainRecyclerView = findViewById(R.id.recyclerViewMainPage);

    }

    @Override
    public void onItemClick(TripTypes item) {
        Intent intent = new Intent(this, MainActivityType.class);
        String url = url(item.getType());
        intent.putExtra("url", url);
        startActivity(intent);

    }

    @Override
    public void onStop(Bundle savedInstanceState) {

        super.onStop();
        //   onStop();
//        Toast.makeText(this,"OnStop",Toast.LENGTH_SHORT).show();
//         sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        int userId=Data.UserID;
//        editor.putInt(EMPTY,userId);
//        if(userId!=-1) {
//            Toast.makeText(this,userId+"i",Toast.LENGTH_SHORT).show();
//            editor.putInt("id", Data.get().getId());
//            editor.putString("name",Data.get().getName());
//            editor.putString("email",Data.get().getEmail());
//            editor.putString("pass",Data.get().getPass());
//            editor.putString("gender",Data.get().getGender());
//            editor.putString("city",Data.get().getCity());
//            editor.apply();
//
//
//        }
//        else{
//            editor.clear();
//        }
        Toast.makeText(this, "name", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onStop(Bundle savedInstanceState) {
//        super.onStop();
//        Toast.makeText(this,"name",Toast.LENGTH_SHORT).show();
//        onStop();
//    }


    public void onLoginClick(View view) {
        Intent intent = new Intent(MainPageActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void onSearchClick(View view) {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();

//    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = sharedpreferences.edit();
//    int userId = Data.UserID;
//    editor.putInt(EMPTY, userId);
//    if (userId != -1) {
//        Toast.makeText(this, userId + "i", Toast.LENGTH_SHORT).show();
//        editor.putInt("id", Data.get().getId());
//        editor.putString("name", Data.get().getName());
//        editor.putString("email", Data.get().getEmail());
//        editor.putString("pass", Data.get().getPass());
//        editor.putString("gender", Data.get().getGender());
//        editor.putString("city", Data.get().getCity());
//        editor.apply();
//
//
//    }
//    else editor.clear();
    }

    private void initializeRecyclerView(List<TripTypes> items) {
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setAdapter(new MainPageAdapter(getApplicationContext(), items, this));
    }
    private class LoadDataAsyncTask extends AsyncTask<Void, Void, List<TripTypes>> {

        @Override
        protected List<TripTypes> doInBackground(Void... voids) {
            // Simulate loading data from a remote server or database
            List<TripTypes> data = new ArrayList<>();
            data.add(new TripTypes(R.drawable.adventure, R.drawable.adventrue_icon, "Adventure"));
            data.add(new TripTypes(R.drawable.cultural, R.drawable.cultural_icon, "Cultural"));
            data.add(new TripTypes(R.drawable.beach4, R.drawable.beach_icon, "Beach"));
            data.add(new TripTypes(R.drawable.entertinment, R.drawable.entertainment_icon, "Entertainment"));
            return data;
        }

        @Override
        protected void onPostExecute(List<TripTypes> result) {
            if (result != null) {
                items = result;
                initializeRecyclerView(items);
            }
        }

    }
}
