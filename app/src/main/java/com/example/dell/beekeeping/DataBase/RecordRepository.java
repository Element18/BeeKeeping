package com.example.dell.beekeeping.DataBase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.dell.beekeeping.Activity.Record;
import com.example.dell.beekeeping.utils.RecordDao;

import java.util.List;

public class RecordRepository {
    private RecordDao recordDao;
    private LiveData<List<RecordSql>> allRecord;

    public RecordRepository(Application application){
        RecordDatabase database = RecordDatabase.getInstance(application);
        recordDao = database.recordDao();
        allRecord = recordDao.getAllRecord();

    }
    public void insert(RecordSql record ){
        new InsertRecordSqlAsyncTask(recordDao).execute(record);

    }
    public void update(RecordSql record){
        new UpdateRecordSqlAsyncTask(recordDao).execute(record);

    }
    public void delete(RecordSql record){
        new DeleteRecordSqlAsyncTask(recordDao).execute(record);
    }
    public void deleteAllRecord(){
        new DeleteAllRecordSqlAsyncTask(recordDao).execute();

    }

    public LiveData<List<RecordSql>> getAllNote() {
        return allRecord;
    }

    private static class InsertRecordSqlAsyncTask extends AsyncTask<RecordSql,Void,Void>{
        private RecordDao recordDao;

        private InsertRecordSqlAsyncTask(RecordDao recordDao){
            this.recordDao =recordDao;

        }

        @Override
        protected Void doInBackground(RecordSql... recordSql) {
            recordDao.insert(recordSql[0]);
            return null;
        }

        }



    private static class UpdateRecordSqlAsyncTask extends AsyncTask<RecordSql,Void,Void> {
        private RecordDao recordDao;

        private UpdateRecordSqlAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;

        }

        @Override
        protected Void doInBackground(RecordSql... recordSql) {
            recordDao.update(recordSql[0]);
            return null;
        }
}

        private static class DeleteRecordSqlAsyncTask extends AsyncTask<RecordSql,Void,Void>{
            private RecordDao recordDao;

            private DeleteRecordSqlAsyncTask(RecordDao recordDao){
                this.recordDao =recordDao;

            }

            @Override
            protected Void doInBackground(RecordSql... recordSql) {
                recordDao.delete(recordSql[0]);
                return null;
            }
        }
    private static class DeleteAllRecordSqlAsyncTask extends AsyncTask<Void,Void,Void>{
        private RecordDao recordDao;

        private DeleteAllRecordSqlAsyncTask(RecordDao recordDao){
            this.recordDao =recordDao;

        }
        @Override
        protected Void doInBackground(Void... voids) {
            recordDao.deleteAllNote();
            return null;
        }
    }
}

