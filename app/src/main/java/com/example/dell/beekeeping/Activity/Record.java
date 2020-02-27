package com.example.dell.beekeeping.Activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.beekeeping.R;
import com.example.dell.beekeeping.fragment.Apiaries;

public class Record extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;
    public static final String EXTRA_ID = "com.example.dell.beekeeping.Activity.EXTRA_ID";
    public static final String EXTRA_ADDRESS = "com.example.dell.beekeeping.Activity.EXTRA_ADDRESS";
    public static final String EXTRA_APIARYNAME = "com.example.dell.beekeeping.Activity.EXTRA_APIARYNAME";
    public static final String EXTRA_TOWN = "com.example.dell.beekeeping.Activity.EXTRA_TOWN";
    public static final String EXTRA_LATITUDE = "com.example.dell.beekeeping.Activity.EXTRA_LATITUDE";
    public static final String EXTRA_LONGITUDE = "com.example.dell.beekeeping.Activity.EXTRA_LONGITUDE";
    public static final String EXTRA_HIVENO = "com.example.dell.beekeeping.Activity.EXTRA_HIVENO";
    public static final String EXTRA_NOOFCOMBS = "com.example.dell.beekeeping.Activity.EXTRA_NOOFCOMBS";
    public static final String EXTRA_QUANTITYOFHONEY = "com.example.dell.beekeeping.Activity.EXTRA_QUANTITYOFHONEY";
    public static final String EXTRA_NOOFPROPOLIS = "com.example.dell.beekeeping.Activity.EXTRA_NOOFPROPOLIS";
    public static final String EXTRA_DATEOFHARVEST = "com.example.dell.beekeeping.Activity.EXTRA_DATEOFHARVEST";
    public static final String EXTRA_DATEOFCOLONISATION = "com.example.dell.beekeeping.Activity.EXTRA_DATEOFCOLONISATION";
    public static final String EXTRA_WAXHARVESTED = "com.example.dell.beekeeping.Activity.EXTRA_WAXHARVESTED";
    public static final String EXTRA_DATE = "com.example.dell.beekeeping.Activity.EXTRA_DATE";

    private EditText addrress,
            apiaryname,
            town,
            longitude,
            latitude,
            hiveNo,
            noOFcombs,
            quantityOfHOney,
            noOfPropolis,
            dateOfharvest,
            dateOfcolonisation,
            waxHarvested;


    public static Button cancel, save;
    Apiaries apiaries;
    String Latitude;
    String Longitude;
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cancel = findViewById(R.id.cancel);
        save = findViewById(R.id.save);
        addrress = findViewById(R.id.address);
        apiaryname = findViewById(R.id.apiaryno);
        town = findViewById(R.id.town);
        longitude = findViewById(R.id.longitude);
        latitude = findViewById(R.id.latitude);
        hiveNo = findViewById(R.id.hivename);
        noOFcombs = findViewById(R.id.combs);
        quantityOfHOney = findViewById(R.id.honey);
        noOfPropolis = findViewById(R.id.propolis);
        dateOfharvest = findViewById(R.id.harvest);
        dateOfcolonisation = findViewById(R.id.colonisation);
        waxHarvested = findViewById(R.id.wax);


        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION
        },REQUEST_LOCATION);

        Intent intent= getIntent();

        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit location");
            addrress.setText(intent.getStringExtra(EXTRA_ADDRESS));
            apiaryname.setText(intent.getStringExtra(EXTRA_APIARYNAME));
            town.setText(intent.getStringExtra(EXTRA_TOWN));
            longitude.setText(intent.getStringExtra(EXTRA_LONGITUDE));
            latitude.setText(intent.getStringExtra(EXTRA_LATITUDE));
        }else{
            setTitle("Add location");
        }



        latitude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    getLocation();
                }else {
                    OnGPS();

                }

            }
        });

        longitude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    GetLocation();
                }else{
                    OnGPS();
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                setResult(RESULT_CANCELED);

            }
        });

    }

    private void GetLocation(){

        if (ActivityCompat.checkSelfPermission(Record.this,Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(Record.this,Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);

        }else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location locationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location locationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (locationGPS!=null){
                double longi = locationGPS.getLongitude();

                Longitude = String .valueOf(longi);
                longitude.setText(Longitude);
            }
            else if (locationNetwork!= null){
                double longi = locationNetwork.getLongitude();

                Longitude = String.valueOf(longi);
                longitude.setText(Longitude);
            }
            else if (locationPassive!= null){
                double longi = locationPassive.getLongitude();

                Longitude = String.valueOf(longi);
                longitude.setText(Longitude);

            }else {

                Toast.makeText(this, "can't get your latitude", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void getLocation () {
        if    ( ActivityCompat.checkSelfPermission(Record.this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED

                && ActivityCompat.checkSelfPermission(Record.this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);
        }else{
            Location locationGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location locationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location locationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (locationGps!= null){

                double lat = locationGps.getLatitude();

                Latitude = String.valueOf(lat);
                latitude.setText(Latitude);

            }else  if (locationNetwork!= null){
                double lat = locationNetwork.getLatitude();

                Latitude = String.valueOf(lat);
                latitude.setText(Latitude);

            }else  if (locationPassive!= null){
                double lat = locationPassive.getLatitude();

                Latitude = String.valueOf(lat);
                latitude.setText(Latitude);
            }
            else {
                Toast.makeText(this, "can't get your latitude", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void OnGPS(){
        final AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPs").setCancelable(false);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void saveNote(){

        String Address = addrress.getText().toString();
        String Apiaryname = apiaryname.getText().toString();
        String Town = town.getText().toString();
        String Latitude = latitude.getText().toString();
        String Longitude = longitude.getText().toString();
        String HiveNo = hiveNo.getText().toString();
        String NoofCombs = noOFcombs.getText().toString();
        String QuantityofHoney = quantityOfHOney.getText().toString();
        String NoofPropolis = noOfPropolis.getText().toString();
        String DateOfHarvest = dateOfharvest.getText().toString();
        String DateOfColonisation = dateOfcolonisation.getText().toString();
        String WaxHarvested = waxHarvested.getText().toString();


        if (Address.isEmpty()|| Apiaryname.isEmpty()|| Town.isEmpty()){
            Toast.makeText(this, "fields can't be empty", Toast.LENGTH_LONG).show();
        }else {

            Intent intent = new Intent();
            intent.putExtra(EXTRA_ADDRESS,Address);
            intent.putExtra(EXTRA_APIARYNAME,Apiaryname);
            intent.putExtra(EXTRA_TOWN,Town);
            intent.putExtra(EXTRA_LONGITUDE,Longitude);
            intent.putExtra(EXTRA_LATITUDE,Latitude);
            intent.putExtra(EXTRA_HIVENO,HiveNo);
            intent.putExtra(EXTRA_NOOFCOMBS,NoofCombs);
            intent.putExtra(EXTRA_QUANTITYOFHONEY,QuantityofHoney);
            intent.putExtra(EXTRA_NOOFPROPOLIS,NoofPropolis);
            intent.putExtra(EXTRA_DATEOFHARVEST,DateOfHarvest);
            intent.putExtra(EXTRA_DATEOFCOLONISATION,DateOfColonisation);
            intent.putExtra(EXTRA_WAXHARVESTED,WaxHarvested);


            int id = getIntent().getIntExtra(EXTRA_ID,-1);
            if (id != -1){
                intent.putExtra(EXTRA_ID,id);
            }
            setResult(RESULT_OK,intent);
            finish();
        }


    }


}
