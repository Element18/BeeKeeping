package com.example.dell.beekeeping.Models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.dell.beekeeping.Activity.Record;
import com.example.dell.beekeeping.DataBase.RecordRepository;
import com.example.dell.beekeeping.DataBase.RecordSql;

import java.util.List;

public class Record_Model extends AndroidViewModel {
    private RecordRepository recordRepository;
    private LiveData<List<RecordSql>> allNote;
    public Record_Model(@NonNull Application application) {
        super(application);
        recordRepository = new RecordRepository(application);
        allNote = recordRepository.getAllNote();
    }

    public RecordSql get(int i)
    {
         return recordRepository.getAllNote().getValue().get(i);

    }
    public void insert(RecordSql recordSql){
        recordRepository.insert(recordSql);
    }

    public void update(RecordSql recordSql){
        recordRepository.update(recordSql);
    }

    public void delete(RecordSql recordSql){
        recordRepository.delete(recordSql);
    }

    public void deleteAllRecord(){
        recordRepository.deleteAllRecord();
    }

    public LiveData<List<RecordSql>> getAllRecord() {
        return allNote;
    }
}
