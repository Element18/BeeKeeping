package com.example.dell.beekeeping.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
@Entity(tableName  ="hive_table")
public class AddHives {


    @PrimaryKey(autoGenerate = true)
    private  int id;





    private String longitude;

    private String latitude;

    private  String dateOfHarvest;
    private  String dateOfColonisation;
    private  String noOfWaxHarvested;
    private  String quantityOfHoneyHarvested;
    private  String noOfPropolis;
    private  String noOfCombs;
    private  String hiveNo;


    public AddHives( String longitude,String latitude, String dateOfHarvest, String dateOfColonisation, String noOfWaxHarvested, String quantityOfHoneyHarvested, String noOfPropolis, String noOfCombs, String hiveNo ) {
        this.dateOfHarvest = dateOfHarvest;
        this.dateOfColonisation = dateOfColonisation;
        this.noOfWaxHarvested = noOfWaxHarvested;
        this.quantityOfHoneyHarvested = quantityOfHoneyHarvested;
        this.noOfPropolis = noOfPropolis;
        this.noOfCombs = noOfCombs;
        this.hiveNo = hiveNo;
        this.latitude = latitude;
        this.longitude = longitude;


    }




    public void setId(int id) {
        this.id = id;

    }

    public int getId() {
        return id;
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
