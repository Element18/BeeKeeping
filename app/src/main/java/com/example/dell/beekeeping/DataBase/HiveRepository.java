package com.example.dell.beekeeping.DataBase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.provider.BaseColumns;

import com.example.dell.beekeeping.Models.Hive_Model;
import com.example.dell.beekeeping.utils.HiveDao;

import java.util.List;

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
import static com.example.dell.beekeeping.DataBase.hive_contract.HiveEntris.col_timestamp;

public class HiveRepository {

//
//    private HiveDao recordDao;
//    private LiveData<List<RecordSql>> allRecord;
//
//    public HiveRepository(Application application){
//        HiveDatabase database = HiveDatabase.getInstance(application);
//        hi = database.recordDao();
//        allRecord = recordDao.getAllRecord();
//
//    }
//    public void insert(RecordSql record ){
//        new RecordRepository.InsertRecordSqlAsyncTask(recordDao).execute(record);
//
//    }
//    public void update(RecordSql record){
//        new RecordRepository.UpdateRecordSqlAsyncTask(recordDao).execute(record);
//
//    }
//    public void delete(RecordSql record){
//        new RecordRepository.DeleteRecordSqlAsyncTask(recordDao).execute(record);
//    }
//    public void deleteAllRecord(){
//        new RecordRepository.DeleteAllRecordSqlAsyncTask(recordDao).execute();
//
//    }
//
//    public LiveData<List<RecordSql>> getAllNote() {
//        return allRecord;
//    }
//
//    private static class InsertRecordSqlAsyncTask extends AsyncTask<RecordSql,Void,Void> {
//        private RecordDao recordDao;
//
//        private InsertRecordSqlAsyncTask(RecordDao recordDao){
//            this.recordDao =recordDao;
//
//        }
//
//        @Override
//        protected Void doInBackground(RecordSql... recordSql) {
//            recordDao.insert(recordSql[0]);
//            return null;
//        }
//
//    }
//
//
//
//    private static class UpdateRecordSqlAsyncTask extends AsyncTask<RecordSql,Void,Void> {
//        private RecordDao recordDao;
//
//        private UpdateRecordSqlAsyncTask(RecordDao recordDao) {
//            this.recordDao = recordDao;
//
//        }
//
//        @Override
//        protected Void doInBackground(RecordSql... recordSql) {
//            recordDao.update(recordSql[0]);
//            return null;
//        }
//    }
//
//    private static class DeleteRecordSqlAsyncTask extends AsyncTask<RecordSql,Void,Void>{
//        private RecordDao recordDao;
//
//        private DeleteRecordSqlAsyncTask(RecordDao recordDao){
//            this.recordDao =recordDao;
//
//        }
//
//        @Override
//        protected Void doInBackground(RecordSql... recordSql) {
//            recordDao.delete(recordSql[0]);
//            return null;
//        }
//    }
//    private static class DeleteAllRecordSqlAsyncTask extends AsyncTask<Void,Void,Void>{
//        private RecordDao recordDao;
//
//        private DeleteAllRecordSqlAsyncTask(RecordDao recordDao){
//            this.recordDao =recordDao;
//
//        }
//        @Override
//        protected Void doInBackground(Void... voids) {
//            recordDao.deleteAllNote();
//            return null;
//        }
//    }





}
