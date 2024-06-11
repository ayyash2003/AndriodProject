package com.example.project;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class addCompany extends AppCompatActivity {

    private static final int REQUEST_CODE_GALLERY = 1;
    private static final int REQUEST_PERMISSION_READ_EXTERNAL_STORAGE = 100;

    private EditText nameTxt, emailTxt, passwordTxt, confirmPasswordTxt, phoneNumberTxt, faceUrlTxt;
    private ImageButton imageButton;
    private ImageView selectedImageView;
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

        imageButton = findViewById(R.id.imageButton3);
        selectedImageView = findViewById(R.id.selectedImageView);

        addCompanyButton = findViewById(R.id.addCompany_button);
        addCompanyButton.setOnClickListener(view -> { //when the user press the add button so will check all the validations and add them to dataBase
            if (checkFields()) {
                if (validatePassword() && validateConfirmPassword() && validateEmail() && validatePhoneNumber() && validateFaceUrl()) {
                    Toast.makeText(addCompany.this, "Company added successfully!", Toast.LENGTH_SHORT).show();

                }
            }
        });
//        public void btnAdd_Click(View view) {
//            String name = nameTxt.getText().toString();
//            String  = nameTxt.getText().toString();
//            String password = passwordTxt.getText().toString();
//            String confirmPassword = confirmPasswordTxt.getText().toString();
//            String phoneNumber = phoneNumberTxt.getText().toString();
//            String faceUrl = faceUrlTxt.getText().toString();
//
//
//            addCompany(name, bookCat, bookPages);
//        }
//
//        private void addCompany(String name, String email, int password,int phoneNumber,Image image){
//            String url = "http://10.0.2.2:84/rest/addbook_json.php";
//            RequestQueue queue = Volley.newRequestQueue(addCompany.this);
//            StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Log.e("TAG", "RESPONSE IS " + response);
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        String msg = jsonObject.getString("message");
//                        Toast.makeText(MainActivity2.this,msg
//                                , Toast.LENGTH_SHORT).show();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }, new com.android.volley.Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    // method to handle errors.
//                    Toast.makeText(addCompany.this,
//                            "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
//                }
//            }) {
//                @Override
//                public String getBodyContentType() {
//                    // as we are passing data in the form of url encoded
//                    // so we are passing the content type below
//                    return "application/x-www-form-urlencoded; charset=UTF-8";
//                }
//
//                @Override
//                protected Map<String, String> getParams() {
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("name", name);
//                    params.put("email", email);
//                    params.put("password", password);
//                    params.put("phone number", phoneNumber);
//                    params.put("image",image);
//                    return params;
//                }
//            };
//            queue.add(request);
//        }

        imageButton.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION_READ_EXTERNAL_STORAGE);
            } else {
                openGallery();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            selectedImageView.setImageURI(selectedImageUri);
        }
    }

    private boolean checkFields() {
        String name = nameTxt.getText().toString();
        String email = emailTxt.getText().toString();
        String password = passwordTxt.getText().toString();
        String confirmPassword = confirmPasswordTxt.getText().toString();
        String phoneNumber = phoneNumberTxt.getText().toString();
        String faceUrl = faceUrlTxt.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phoneNumber.isEmpty() || faceUrl.isEmpty()) {
            Toast.makeText(addCompany.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        String password = passwordTxt.getText().toString();
        if (password.length() < 6) {
            Toast.makeText(addCompany.this, "Your password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateConfirmPassword() {
        String password = passwordTxt.getText().toString();
        String confirmPassword = confirmPasswordTxt.getText().toString();
        if (!password.equals(confirmPassword)) {
            Toast.makeText(addCompany.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateEmail() {
        String email = emailTxt.getText().toString().trim();
        if (!email.toLowerCase().endsWith("@qmta")) {
            Toast.makeText(addCompany.this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validatePhoneNumber() {
        String phoneNumber = phoneNumberTxt.getText().toString();
        if (!phoneNumber.startsWith("059") || phoneNumber.length() != 10) {
            if (!phoneNumber.startsWith("059")) {
                Toast.makeText(addCompany.this, "The phone number must start with '059'", Toast.LENGTH_SHORT).show();
            } else if (phoneNumber.length() != 10) {
                Toast.makeText(addCompany.this, "The phone number must be 10 digits long", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
        return true;
    }

    private boolean validateFaceUrl() {
        String faceUrl = faceUrlTxt.getText().toString();
        if (!faceUrl.startsWith("https://www.facebook.com/")) {
            Toast.makeText(addCompany.this, "Invalid Facebook URL", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
