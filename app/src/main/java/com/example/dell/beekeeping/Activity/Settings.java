package com.example.dell.beekeeping.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dell.beekeeping.R;

public class Settings extends AppCompatActivity {

    private LinearLayout layout;
    private LinearLayout layout1;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        layout = findViewById(R.id.linearlayout2);
        layout1 = findViewById(R.id.linearlayout3);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                startActivity(new Intent(Settings.this,BackUp.class));
            }
        });

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                startActivity(new Intent (Settings.this,Restore.class));
            }
        });
    }
}
