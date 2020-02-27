package com.example.dell.beekeeping.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.dell.beekeeping.Models.Image_Model;

public class DbOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "imageDB";
    public static final int VERSION = 1;
    public static final String TEXT_TYPE = "text";
    public static final String INTEGR = "integer";
    public static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            " CREATE TABLE "+ Image_Contract.MemoryEntries.TABLE_NAME +" ("+
                    Image_Contract.MemoryEntries._ID + INTEGR + "PRIMARY_KEY"  + COMMA_SEP +
                    Image_Contract.MemoryEntries.COLOUMN_CAPTION + TEXT_TYPE + COMMA_SEP +
                    Image_Contract.MemoryEntries.COLOUMN_IMAGE + TEXT_TYPE +  ")";





    public DbOpenHelper (Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Image_Contract.MemoryEntries.TABLE_NAME);
        onCreate(db);
    }

    public Cursor readAllMemory(){
        SQLiteDatabase db = getWritableDatabase();

        return db.query(
                Image_Contract.MemoryEntries.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public boolean addToMemory(Image_Model image_model){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Image_Contract.MemoryEntries.COLOUMN_CAPTION,image_model.getCaption());
        contentValues.put(Image_Contract.MemoryEntries.COLOUMN_IMAGE,image_model.getImageAsString());

        Long result =  db.insert(Image_Contract.MemoryEntries.TABLE_NAME,null,contentValues);

        if (result == -1)
           return false;
        else
            return true;
        
    }
}
