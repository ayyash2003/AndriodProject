package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignInPage extends AppCompatActivity {

    TextView nameLabel, emailLabel, passwordLabel, confirmPasswordLabel, genderLabel, cityLabel;
    EditText nameTxt, emailTxt, passwordTxt, confirmPasswordTxt;
    RadioGroup genderGroup;
    RadioButton maleBtn, femaleBtn;
    Spinner citySpinner;
    Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        nameLabel = findViewById(R.id.name_label);
        emailLabel = findViewById(R.id.email_label);
        passwordLabel = findViewById(R.id.password_label);
        confirmPasswordLabel = findViewById(R.id.confirm_password_label);
        genderLabel = findViewById(R.id.gender_label);
        cityLabel = findViewById(R.id.city_label);

        nameTxt = findViewById(R.id.name);
        emailTxt = findViewById(R.id.email);
        passwordTxt = findViewById(R.id.password);
        confirmPasswordTxt = findViewById(R.id.confirm_password);

        genderGroup = findViewById(R.id.gender_group);
        maleBtn = findViewById(R.id.male);
        femaleBtn = findViewById(R.id.female);

        citySpinner = findViewById(R.id.city_spinner);
        createAccountButton = findViewById(R.id.create_account_button);

        String[] cities = {"Ramallah", "Nablus", "Jericho", "Hebron", "Jenin","Bethlehem"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapter);

        createAccountButton.setOnClickListener(view -> {
            String name = nameTxt.getText().toString();
            String email = emailTxt.getText().toString();
            String password = passwordTxt.getText().toString();
            String confirmPassword = confirmPasswordTxt.getText().toString();
            String city = citySpinner.getSelectedItem().toString();
            int selectedGenderId = genderGroup.getCheckedRadioButtonId();
            RadioButton selectedGenderButton = findViewById(selectedGenderId);
            String gender = (selectedGenderButton != null) ? selectedGenderButton.getText().toString() : "";

            // Check fields and confirmation password
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || gender.isEmpty()) {
                Toast.makeText(SignInPage.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(SignInPage.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {

                addLoginInfo(name, email, password, gender, city);

                Intent intent=new Intent(this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void addLoginInfo(String name, String email, String password, String gender, String city) {
        String url = "http://192.168.1.103/Android/user.php";
        RequestQueue queue = Volley.newRequestQueue(SignInPage.this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String msg = jsonObject.getString("message");
                } catch (JSONException e) {
                    Toast.makeText(SignInPage.this, "getMessage "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignInPage.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("gender", gender);
                params.put("city", city);
                return params;
            }
        };
        queue.add(request);
    }
}
