package com.example.fribanator;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ScoreRepository {
    private ScoreDao scoreDao;
    private LiveData<List<Score>> allScores;

    public ScoreRepository(Application application) {
        RataDatabase database = RataDatabase.getInstance(application);
        scoreDao = database.scoreDao();
        allScores = scoreDao.getAllScores();
    }

    public void insert(Score score) {
        new InsertScoreAsyncTask(scoreDao).execute(score);
    }

    public LiveData<List<Score>> getAllScores() {
        return allScores;
    }


    private static class InsertScoreAsyncTask extends AsyncTask<Score, Void, Void> {
        private ScoreDao scoreDao;

        private InsertScoreAsyncTask(ScoreDao scoreDao) {
            this.scoreDao = scoreDao;
        }

        @Override
        protected Void doInBackground(Score... scores) {
            scoreDao.insert(scores[0]);
            return null;
        }
    }

}
