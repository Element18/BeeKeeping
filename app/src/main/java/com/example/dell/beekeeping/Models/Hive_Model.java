package com.example.dell.beekeeping.Models;

import android.widget.EditText;

public class Hive_Model {
    public static String HiveNo;
    public  static  String NoOfcombs,
    DateOfColonisation,
    DateOfHarvest,
    NoOfWax,
    QuantityOfHoney,
    NoOfPropolis,
    Latitude,
    Longitude;

    public Hive_Model( String hiveNo, String noOfcombs, String dateOfColonisation,
                       String dateOfHarvest, String noOfWax, String quantityOfHoney,
                       String noOfPropolis, String latitude, String longitude) {
        this.HiveNo = hiveNo;
        this.NoOfcombs = noOfcombs;
        this.DateOfColonisation = dateOfColonisation;
        this.DateOfHarvest = dateOfHarvest;
        this.NoOfWax = noOfWax;
        this.QuantityOfHoney = quantityOfHoney;
        this.NoOfPropolis = noOfPropolis;
        this.Latitude = latitude;
        this.Longitude = longitude;
    }

    public static String getHiveNo() {
        return HiveNo;
    }

    public static String getNoOfcombs() {
        return NoOfcombs;
    }

    public static String getDateOfColonisation() {
        return DateOfColonisation;
    }

    public static String getDateOfHarvest() {
        return DateOfHarvest;
    }

    public static String getNoOfWax() {
        return NoOfWax;
    }

    public static String getQuantityOfHoney() {
        return QuantityOfHoney;
    }

    public static String getNoOfPropolis() {
        return NoOfPropolis;
    }

    public static String getLatitude() {
        return Latitude;
    }

    public static String getLongitude() {
        return Longitude;
    }
}
