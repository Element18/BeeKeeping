package com.example.dell.beekeeping.utils;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;



import com.example.dell.beekeeping.DataBase.RecordSql;

import java.util.List;

@Dao
public interface RecordDao {
    @Insert
    void insert(RecordSql record);

    @Update
    void update(RecordSql record);

    @Delete
    void delete(RecordSql record);

    @Query("DELETE FROM record_table")
    void deleteAllNote();

    @Query("SELECT * FROM record_table ORDER BY address ASC")
    LiveData<List<RecordSql>> getAllRecord();



}
