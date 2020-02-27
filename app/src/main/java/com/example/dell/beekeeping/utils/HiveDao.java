package com.example.dell.beekeeping.utils;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dell.beekeeping.DataBase.AddHives;


import java.util.List;

public interface HiveDao {

    @Insert
    void insert(AddHives addHives);

    @Update
    void update(AddHives addHives);

    @Delete
    void delete(AddHives addHives);

    @Query("DELETE FROM hive_table")
    void deleteAllHives();

    @Query("SELECT * FROM hive_table ORDER BY dateOfHarvest ASC")
    LiveData<List<AddHives>> getAllHives();
}
