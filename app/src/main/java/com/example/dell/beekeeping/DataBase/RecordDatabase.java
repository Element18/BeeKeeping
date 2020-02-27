package com.example.dell.beekeeping.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.dell.beekeeping.utils.RecordDao;

@Database(entities =  {RecordSql.class},version = 2, exportSchema = false)
public abstract class RecordDatabase extends RoomDatabase {

    private static RecordDatabase instance;

    public abstract RecordDao recordDao();
    public static synchronized RecordDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RecordDatabase.class,"Record_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
