package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtPassword;
    private TextView txtMsg;
    SharedPreferences sharedpreferences;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String EMAIL = "EmailKey";
    private String email;
    private String password;
    private String BASE_URL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        //   sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        set();
//        String email = sharedpreferences.getString(EMAIL,"");
//        edtName.setText(email);

    }

    private void set() {
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        txtMsg = findViewById(R.id.txtMsg);
    }

    //    public void onClickCreateAccount(View view){
//        Intent intent =new Intent(this,SignUpActivity.class);
//        startActivity(intent);
//    }
    private String url(String type, String email, String password) {
        return "http://192.168.1.103/Android/check.php?type=" + type + "&email=" + email + "&pass=" + password;
    }

    public void onClickLogin(View view) {
        email = edtName.getText().toString();
        password = edtPassword.getText().toString();
        if (check()) {
            char user = user(email, password);
            if (user == 'u') {
                BASE_URL = url("u", email, password);

                loadItems('u');

                Toast.makeText(this, "FD", Toast.LENGTH_SHORT).show();
            } else if (user == 'a') {
                if (email.equals("qmta@admin.com") && password.equals("qmta****")) {
                    Intent intent = new Intent(this, addCompany.class);
                    startActivity(intent);
                } else {
                    txtMsg.setText("تاكدلي من البريد والرقم السري بعد اذنك");
                }

            } else if (user == 'c') {

                Toast.makeText(this, "Company", Toast.LENGTH_SHORT).show();
                BASE_URL = url("c", email, password);
                loadItems('c');

            } else
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();


        } else {
            txtMsg.setText("بعينك الله عبي الفاضي ");

        }
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//
//        editor.putString(EMAIL, email);
//        editor.apply();

    }

    private void loadItems(char type) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            Toast.makeText(LoginActivity.this, "FD2", Toast.LENGTH_SHORT).show();

                            JSONArray array = new JSONArray(response);
                            Toast.makeText(LoginActivity.this, "FD3", Toast.LENGTH_SHORT).show();

//                            for (int i = 0; i<array.length(); i++){
//
//                                JSONObject object = array.getJSONObject(i);
//
//                                String email = object.getString("title");
//                                String password = object.getString("type");
//                                String name = object.getString("destination");
//
//
//
//
//                                Trips trips = new Trips(title,type,destination, image,startingpoint,duration,startdate,enddate,price,risk,description,checkbox);
//                                items.add(trips);
//                            }
                            if (array.length() == 0) {
                                txtMsg.setText("يا حبيبي فش عنا حساب بهاد البريد والرقم السري");
//Qais
                            } else if (array.length() == 1) {
                                Toast.makeText(LoginActivity.this, "DD", Toast.LENGTH_SHORT).show();
                                if (type == 'u') {
                                    JSONObject object = array.getJSONObject(0);

                                    Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
                                    String name = object.getString("name");
                                    intent.putExtra("name", name);
                                    startActivity(intent);

                                    Toast.makeText(LoginActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e) {
                            Toast.makeText(LoginActivity.this, "DD", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("TAG", "" + error);

                Toast.makeText(LoginActivity.this, "err"+error.toString(), Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(LoginActivity.this).add(stringRequest);


    }



    public boolean check(){
       if( !email.isEmpty()&&!password.isEmpty()){
           return true;
       }
       return false;
    }
    public char user(String email ,String password){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(email.length()>9 && email.matches(emailPattern)){
           if(email.substring(email.length()-9,email.length()).equals("@qmta.com")){
               return 'c';
            }
           else if (email.substring(email.length()-10,email.length()).equals("@admin.com"))
               return 'a';
           else{
               if(email.contains("@")&&email.contains(".")){
                   return 'u';
               }
               else return 'e';
           }
        }
        else
            return 'e';

    }
    public void onClickCreateAccount(View view){
        Intent intent =new Intent(this,SignInPage.class);
        startActivity(intent);
    }
}