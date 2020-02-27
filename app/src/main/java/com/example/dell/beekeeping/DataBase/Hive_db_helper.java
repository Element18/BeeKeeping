package com.example.dell.beekeeping.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dell.beekeeping.Models.Hive_Model;

import static android.provider.BaseColumns._ID;
import static com.example.dell.beekeeping.DataBase.hive_contract.HiveEntris.HIVE_TABLE_NAME;
import static com.example.dell.beekeeping.DataBase.hive_contract.HiveEntris.col_10;
import static com.example.dell.beekeeping.DataBase.hive_contract.HiveEntris.col_11;
import static com.example.dell.beekeeping.DataBase.hive_contract.HiveEntris.col_12;
import static com.example.dell.beekeeping.DataBase.hive_contract.HiveEntris.col_13;

import static com.example.dell.beekeeping.DataBase.hive_contract.HiveEntris.col_6;
import static com.example.dell.beekeeping.DataBase.hive_contract.HiveEntris.col_7;
import static com.example.dell.beekeeping.DataBase.hive_contract.HiveEntris.col_8;
import static com.example.dell.beekeeping.DataBase.hive_contract.HiveEntris.col_9;
import static com.example.dell.beekeeping.DataBase.hive_contract.HiveEntris.col_longitude;
import static com.example.dell.beekeeping.DataBase.hive_contract.HiveEntris.col_timestamp;

public class Hive_db_helper extends SQLiteOpenHelper {

    public static  final  String DATABASE_NAME = "hive.db";
    public Hive_db_helper( Context context ) {
        super(context, DATABASE_NAME, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_HIVE_TABLE = " CREATE TABLE " +
                hive_contract.HiveEntris.HIVE_TABLE_NAME + "( " +
                hive_contract.HiveEntris._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                hive_contract.HiveEntris.col_6 + "TEXT ," +
                hive_contract.HiveEntris.col_7 + " TEXT," +
                hive_contract.HiveEntris.col_8 + " TEXT ," +
                hive_contract.HiveEntris.col_9 + " TEXT ," +
                hive_contract.HiveEntris.col_10 + " TEXT ," +
                hive_contract.HiveEntris.col_11 + " TEXT ," +
                hive_contract.HiveEntris.col_12 + " TEXT ,"+
                hive_contract.HiveEntris.col_13 + " string ," +
                hive_contract.HiveEntris.col_longitude + "string  , " +
                hive_contract.HiveEntris.col_timestamp + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        sqLiteDatabase.execSQL(CREATE_HIVE_TABLE);

    }

//    private static final String DATABASE_ALTER_LATITUDE = "ALTER TABLE "
//            + HIVE_TABLE_NAME + " ADD COLUMN " + col_13 + " string;";

    private static final String DATABASE_ALTER_LONGITUDE = "ALTER TABLE "
            + HIVE_TABLE_NAME + " ADD COLUMN " + col_longitude + " string  ;";

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion ) {

        if (oldVersion < 5) {
            sqLiteDatabase.execSQL(DATABASE_ALTER_LONGITUDE);
        }
//        if (oldVersion < 3) {
//            db.execSQL(DATABASE_ALTER_TEAM_2);
//        }
    }

    public boolean insertHivedata(Hive_Model hive_model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_6,hive_model.HiveNo);
        values.put(col_7,hive_model.NoOfcombs);
        values.put(col_8,hive_model.DateOfColonisation);
        values.put(col_9,hive_model.DateOfHarvest);
        values.put(col_10,hive_model.NoOfWax);
        values.put(col_11,hive_model.QuantityOfHoney);
        values.put(col_12,hive_model.NoOfPropolis);
        values.put(col_13,hive_model.Latitude);
        values.put(col_longitude,hive_model.Longitude);

         Long res = db.insert(HIVE_TABLE_NAME,null, values);

         if (res == -1){
             return false;
         }else {
             return true;
         }


    }
}
