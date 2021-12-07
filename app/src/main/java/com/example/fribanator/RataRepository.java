package com.example.fribanator;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RataRepository {
    private RataDao rataDao;
    private LiveData<List<Rata>> allRatas;

    public RataRepository(Application application) {
        RataDatabase database = RataDatabase.getInstance(application);
        rataDao = database.rataDao();
        allRatas = rataDao.getAllRatas();
    }

    public void insert(Rata rata) {
        new InsertRataAsyncTask(rataDao).execute(rata);
    }

    public void update(Rata rata) {
        new UpdateRataAsyncTask(rataDao).execute(rata);
    }

    public void delete(Rata rata) {
        new DeleteRataAsyncTask(rataDao).execute(rata);
    }

    public void deleteAllRatas() {
        new DeleteAllRatasAsyncTask(rataDao).execute();
    }

    public LiveData<List<Rata>> getAllRatas() {
        return allRatas;
    }


    private static class InsertRataAsyncTask extends AsyncTask<Rata, Void, Void> {
        private RataDao rataDao;

        private InsertRataAsyncTask(RataDao rataDao) {
            this.rataDao = rataDao;
        }

        @Override
        protected Void doInBackground(Rata... currencies) {
            rataDao.insert(currencies[0]);
            return null;
        }
    }

    private static class UpdateRataAsyncTask extends AsyncTask<Rata, Void, Void> {
        private RataDao rataDao;

        private UpdateRataAsyncTask(RataDao rataDao) {
            this.rataDao = rataDao;
        }

        @Override
        protected Void doInBackground(Rata... currencies) {
            rataDao.update(currencies[0]);
            return null;
        }
    }

    private static class DeleteRataAsyncTask extends AsyncTask<Rata, Void, Void> {
        private RataDao rataDao;

        private DeleteRataAsyncTask(RataDao rataDao) {
            this.rataDao = rataDao;
        }

        @Override
        protected Void doInBackground(Rata... radat) {
            rataDao.delete(radat[0]);
            return null;
        }
    }

    private static class DeleteAllRatasAsyncTask extends AsyncTask<Void, Void, Void> {
        private RataDao rataDao;

        private DeleteAllRatasAsyncTask(RataDao rataDao) {
            this.rataDao = rataDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            rataDao.deleteAllRatas();
            return null;
        }
    }


}
