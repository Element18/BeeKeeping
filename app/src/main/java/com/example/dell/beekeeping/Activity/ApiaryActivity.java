package com.example.dell.beekeeping.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.beekeeping.DataBase.Hive_db_helper;
import com.example.dell.beekeeping.Models.Hive_Model;
import com.example.dell.beekeeping.R;

public class ApiaryActivity extends AppCompatActivity {

    Hive_db_helper db;
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    private EditText editTextHiveNo ,
            editTextNoOfcombs,
            editTextDateOfColonisation,
            editTextDateOfHarvest,
            editTextNoOfWax,
            editTextQuantityOfHoney,
            editTextNoOfPropolis,
            editTextLatitude,
            editTextLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new Hive_db_helper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiary);
        toolbar = findViewById(R.id.tool_bar1);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        floatingActionButton = findViewById(R.id.hive_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputHiveDetails();
            }
        });

        String town = getIntent().getStringExtra("town");
        String address = getIntent().getStringExtra("address");
        String latitude = getIntent().getStringExtra("latitude");
        String longitude = getIntent().getStringExtra("longitude");
        String apiaryno = getIntent().getStringExtra("apiaryno");


        TextView Address = findViewById(R.id.AddressHead);
        Address.setText(address);

        TextView ApiaryName = findViewById(R.id.ApiaryName1);
        ApiaryName.setText(apiaryno);

        TextView Town = findViewById(R.id.Town1);
        Town.setText(town);


        TextView Latitude = findViewById(R.id.Latitude1);
        Latitude.setText(latitude);

        TextView Longitude = findViewById(R.id.Longitude1);
        Longitude.setText(longitude);


    }

    public void inputHiveDetails(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        final  View view = layoutInflater.inflate(R.layout.apairy_custom_layout,null);
        builder.setView(view);

        this.editTextHiveNo = (EditText)view.findViewById(R.id.hivenameHive);
        this.editTextNoOfcombs = (EditText)view. findViewById(R.id.combsHives);
        this.editTextDateOfColonisation = (EditText)view.findViewById(R.id.colonisationHives);
        this.editTextDateOfHarvest = (EditText)view.findViewById(R.id.harvestHives);
        this.editTextNoOfWax = (EditText)view.findViewById(R.id.waxHives);
        this.editTextQuantityOfHoney = (EditText)view.findViewById(R.id.honeyHives);
        this.editTextNoOfPropolis = (EditText)view. findViewById(R.id.propolisHives);
        this.editTextLatitude = view.findViewById(R.id.latitudeHives);
        this.editTextLongitude = view.findViewById(R.id.longitudeHives);

        builder.setPositiveButton("save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialog, int which) {

                String gottenHiveno =  editTextHiveNo.getText().toString();
                String gottencombsno = editTextNoOfcombs.getText().toString();
                String gottencolonisationDate = editTextDateOfColonisation.getText().toString();
                String gottenharvestDate = editTextDateOfHarvest.getText().toString();
                String gottenwax = editTextNoOfWax.getText().toString();
                String gottenhoneyquantity = editTextQuantityOfHoney.getText().toString();
                String gottenpropolis = editTextNoOfPropolis.getText().toString();
                String gottenLatitude = editTextLatitude.getText().toString();
                String gottenLongitude = editTextLongitude.getText().toString();

               AddToView(gottenHiveno,gottencombsno,gottencolonisationDate,
                        gottenharvestDate,gottenwax,gottenhoneyquantity,gottenpropolis,gottenLatitude,
                        gottenLongitude);
            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    private void AddToView(String HiveNo , String NoOfCombs, String DateOfolonisation, String DateOfHarvest,String NoOfWax, String QuantityOfHarvest, String NoOfPropolis, String Latitude, String Longitude){

        db = new Hive_db_helper(this);
        Hive_Model hive_model = new Hive_Model(HiveNo,NoOfCombs,DateOfolonisation,DateOfHarvest,NoOfWax,QuantityOfHarvest,NoOfPropolis,Latitude,Longitude);
        boolean data_inserted = db.insertHivedata(hive_model);

        if (data_inserted)
            Toast.makeText(this, "data is Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "data  not inserted", Toast.LENGTH_LONG).show();

//        this.recyclerView = findViewById(R.id.recyclerView);
//        LocationAdapter locationAdapter;
//        locationAdapter = new LocationAdapter(this,this.db.getLocationData(),databaseActivity);
//        this.recyclerView.setAdapter(locationAdapter);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(llm);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.apiary_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_edit){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}