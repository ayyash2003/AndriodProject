package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class addCompany extends AppCompatActivity {

    private EditText nameTxt, emailTxt, passwordTxt, confirmPasswordTxt, phoneNumberTxt, faceUrlTxt;
    private Button addCompanyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);

        nameTxt = findViewById(R.id.name);
        emailTxt = findViewById(R.id.email);
        passwordTxt = findViewById(R.id.password);
        confirmPasswordTxt = findViewById(R.id.confirm_password);
        phoneNumberTxt = findViewById(R.id.phoneNumber);
        faceUrlTxt = findViewById(R.id.faceUrl);

        addCompanyButton = findViewById(R.id.addCompany_button);
        addCompanyButton.setOnClickListener(view -> {
            if (validateInput()) {
                String name = nameTxt.getText().toString();
                String email = emailTxt.getText().toString();
                String password = passwordTxt.getText().toString();
                String phoneNumber = phoneNumberTxt.getText().toString();
                String faceUrl = faceUrlTxt.getText().toString();
                addCompanyInfo(name, email, password, phoneNumber, faceUrl);
            }
        });
    }

    private boolean validateInput() {
        String name = nameTxt.getText().toString();
        String email = emailTxt.getText().toString();
        String password = passwordTxt.getText().toString();
        String confirmPassword = confirmPasswordTxt.getText().toString();
        String phoneNumber = phoneNumberTxt.getText().toString();
        String faceUrl = faceUrlTxt.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phoneNumber.isEmpty() || faceUrl.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.length() < 6) {
            Toast.makeText(this, "Your password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!email.toLowerCase().endsWith("@qmta.com")) {
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!phoneNumber.startsWith("059") && !phoneNumber.startsWith("056") || phoneNumber.length() != 10) {
            if (!phoneNumber.startsWith("059") && !phoneNumber.startsWith("056")) {
                Toast.makeText(this, "The phone number must start with '059' or '056'", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "The phone number must be 10 digits long", Toast.LENGTH_SHORT).show();
            }
            return false;


    } else if (!faceUrl.startsWith("https://www.facebook.com/")) {
            Toast.makeText(this, "Invalid Facebook URL", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void addCompanyInfo(String name, String email, String password, String phoneNumber, String faceUrl) {
        String url = "http://192.168.56.1/android/addCompany.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        Toast.makeText(addCompany.this, "Company added successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(addCompany.this, "Failed to add company", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(addCompany.this, "Error parsing JSON response", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(addCompany.this, "Failed to connect to server: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                params.put("phoneNumber", phoneNumber);
                params.put("faceUrl", faceUrl);
                return params;
            }
        };
        queue.add(request);
    }
}
