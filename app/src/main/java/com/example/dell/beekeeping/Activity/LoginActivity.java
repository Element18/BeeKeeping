package com.example.dell.beekeeping.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class LoginActivity extends AppCompatActivity {
     private EditText userName,passWord;
     private Button signInBtn,createAccount;
     private TextView forgotPassWord;
     private ProgressBar progressBar;
     private CheckBox checkBox;
    final String signInUrl = "http://localhost/element/signin.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.name);
        passWord = findViewById(R.id.password);
        signInBtn = findViewById(R.id.signIn);
        createAccount = findViewById(R.id.account);
        forgotPassWord = findViewById(R.id.forgotPassword);
        progressBar = findViewById(R.id.progressbar);


        signInBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String gottenuserName = userName.getText().toString();
                String gottenPassword = passWord.getText().toString();
                progressBar.setVisibility(View.VISIBLE);

                if (gottenuserName.trim().isEmpty()|| gottenPassword.trim().isEmpty()){
                    Toast.makeText(LoginActivity.this, "plz input username or password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }else  {
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }
        });

            createAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(LoginActivity.this,CreateAccount.class));

                }
            });

            forgotPassWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
                }
            });
    }

    private void setSignInBtn(){
        StringRequest signinRequest = new StringRequest(Request.Method.POST, signInUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Successful")) {
                    progressBar.setVisibility(View.VISIBLE);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else if (response.trim().equals("password or username dosent exist")){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                }

                  }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(LoginActivity.this,"no internet connection",Toast.LENGTH_LONG).show();

            }

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map = new HashMap<>();
                String gottenuserName = userName.getText().toString();
                String gottenPassword = passWord.getText().toString();
//                map.put("email",gottenuserName);
                map.put("password",gottenPassword);
                map.put("username",gottenuserName);

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(signinRequest);
    }


}
