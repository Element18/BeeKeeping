package com.example.dell.beekeeping.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.beekeeping.R;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity {
    private EditText username, email, phonenumber, password;
    private Button signUpBtn;
    final String register = "http://10.0.2.2/element/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        username = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phonenumber = findViewById(R.id.number);
        password = findViewById(R.id.password);
        signUpBtn = findViewById(R.id.signupbtn);



        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getUserName = username.getText().toString();
                String getEmail = email.getText().toString();
                String getPhoneNumber = phonenumber.getText().toString();
                String getPass = password.getText().toString();
                if (getUserName.isEmpty()|| getEmail.isEmpty()||getPass.isEmpty()||getPhoneNumber.isEmpty()){
                    Toast.makeText(CreateAccount.this, "plz a field is empty", Toast.LENGTH_SHORT).show();
                }else {
                    setCreateAccount();
                }

            }
        });

    }

    private void setCreateAccount(){

        StringRequest request = new StringRequest(Request.Method.POST, register, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("sign up successful")) {
                    Toast.makeText(CreateAccount.this, "sign up successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CreateAccount.this, MainActivity.class));
                }else if (response.trim().equals("password already exist plz choose another")){
                    Toast.makeText(CreateAccount.this, response, Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(CreateAccount.this, response, Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CreateAccount.this, "no internet connection", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                String getUserName = username.getText().toString();
                String getEmail = email.getText().toString();
                String getPhoneNumber = phonenumber.getText().toString();
                String getPass = password.getText().toString();
                map.put("email", getEmail);
                map.put("username",getUserName);
                map.put("password",getPass);
                map.put("phonenumber", getPhoneNumber);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


}
