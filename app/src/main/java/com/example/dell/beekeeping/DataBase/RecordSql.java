package com.example.dell.beekeeping.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "record_table")


public class RecordSql   {

    @PrimaryKey(autoGenerate = true)
    private  int id;



    private String address;

    private String apiaryNo;

    private String town;

    private String longitude;

    private String latitude;

    private  String dateOfHarvest;
    private  String dateOfColonisation;
    private  String noOfWaxHarvested;
    private  String quantityOfHoneyHarvested;
    private  String noOfPropolis;
    private  String noOfCombs;
    private  String hiveNo;


    public RecordSql(String address, String apiaryNo, String town, String longitude,String latitude, String dateOfHarvest, String dateOfColonisation, String noOfWaxHarvested, String quantityOfHoneyHarvested, String noOfPropolis, String noOfCombs, String hiveNo ) {
        this.dateOfHarvest = dateOfHarvest;
        this.dateOfColonisation = dateOfColonisation;
        this.noOfWaxHarvested = noOfWaxHarvested;
        this.quantityOfHoneyHarvested = quantityOfHoneyHarvested;
        this.noOfPropolis = noOfPropolis;
        this.noOfCombs = noOfCombs;
        this.hiveNo = hiveNo;
        this.address = address;
        this.apiaryNo = apiaryNo;
        this.town = town;
        this.latitude = latitude;
        this.longitude = longitude;


    }




    public void setId(int id) {
        this.id = id;

    }

    public int getId() {
        return id;
    }


    public String getAddress() {
        return address;
    }



    public String getApiaryNo() {
        return apiaryNo;
    }

    public String getTown() {
        return town;

    }


    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getDateOfColonisation () {
        return dateOfColonisation;
    }

    public String getNoOfWaxHarvested () {
        return noOfWaxHarvested;
    }

    public String getQuantityOfHoneyHarvested () {
        return quantityOfHoneyHarvested;
    }

    public String getNoOfPropolis () {
        return noOfPropolis;
    }

    public String getNoOfCombs () {
        return noOfCombs;
    }

    public String getHiveNo () {
        return hiveNo;
    }

   public String getDateOfHarvest(){
        return dateOfHarvest;
   }
}
