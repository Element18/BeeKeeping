package com.example.dell.beekeeping.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText EMAIL,USERNAME, PHONENUMBER, NEWPASSWORD ;
    private Button recover;
    final String forgotPassword = "http://10.0.2.2/element/forgotPassword.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        EMAIL=findViewById(R.id.email1);
        USERNAME=findViewById(R.id.username1);
        PHONENUMBER=findViewById(R.id.phonenumber1);
        NEWPASSWORD=findViewById(R.id.newpassword);
        recover=findViewById(R.id.recover);

        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gottenEmail = EMAIL.getText().toString();
                String gottenUsername = USERNAME.getText().toString();
                String gottenPhoneNumber = PHONENUMBER.getText().toString();
                String gottenNewPassword = NEWPASSWORD.getText().toString();
                if (gottenEmail.isEmpty()|| gottenPhoneNumber.isEmpty()|| gottenUsername.isEmpty()|| gottenNewPassword.isEmpty()){
                    Toast.makeText(ForgotPasswordActivity.this, "A field is empty plz fill out to proceed", Toast.LENGTH_SHORT).show();
                }else {
                    setRecover();
                }
            }
        });
    }

    private void setRecover(){
        StringRequest recoverRequest = new StringRequest(Request.Method.POST, forgotPassword, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("recovery successful")) {
                    Toast.makeText(ForgotPasswordActivity.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgotPasswordActivity.this, MainActivity.class));
                } else if (response.trim().equals("recovery failed check internet connection")) {
                    Toast.makeText(ForgotPasswordActivity.this, response, Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ForgotPasswordActivity.this,"no internet connection",Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map = new HashMap<>();
                String gottenEmail = EMAIL.getText().toString();
                String gottenUsername = USERNAME.getText().toString();
                String gottenPhonenumber = PHONENUMBER.getText().toString();
                String gottenNewpassword = NEWPASSWORD.getText().toString();
                map.put("email",gottenEmail);
                map.put("phonenumber",gottenPhonenumber);
                map.put("username",gottenUsername);
                map.put("newpassword",gottenNewpassword);
                return super.getParams();
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(recoverRequest);


    }
}
