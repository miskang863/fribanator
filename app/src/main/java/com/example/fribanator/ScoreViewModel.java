package com.example.fribanator;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class ScoreViewModel extends AndroidViewModel {
    private ScoreRepository repository;
    private LiveData<List<Score>> allScores;
    private final MutableLiveData<Score> selected = new MutableLiveData<Score>();

    public int scoreNumber = 0;

    public Integer currentFairway = 0;

    public ArrayList<Integer> scoreList = new ArrayList<>();

    public ScoreViewModel(@NonNull Application application) {
        super(application);
        repository = new ScoreRepository(application);
        allScores = repository.getAllScores();
    }

    public void insert(Score score) {
        repository.insert(score);
    }

    public LiveData<List<Score>> getAllScores() {
        return allScores;
    }

    public void select(Score score) {
        selected.setValue(score);
    }

    public LiveData<Score> getSelected() {
        return selected;
    }

    public void plusScoreNumber() {
        scoreNumber++;
    }

    public void minusScoreNumber() {
        scoreNumber--;
    }

    public void resetScoreNumber() {
        scoreNumber = 0;
    }

    public void plusCurrentFairway() {
        currentFairway++;
    }

    public void resetCurrentFairway() {
        currentFairway = 0;
    }




}
