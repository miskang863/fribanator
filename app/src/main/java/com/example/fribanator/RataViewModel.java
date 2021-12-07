package com.example.fribanator;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class RataViewModel extends AndroidViewModel {
    private RataRepository repository;
    private LiveData<List<Rata>> allRatas;
    private final MutableLiveData<Rata> selected = new MutableLiveData<Rata>();


    public RataViewModel(@NonNull Application application) {
        super(application);
        repository = new RataRepository(application);
        allRatas = repository.getAllRatas();
    }

    public void insert(Rata rata) {
        repository.insert(rata);
    }

    public void update(Rata rata) {
        repository.update(rata);
    }

    public void delete(Rata rata) {
        repository.delete(rata);
    }

    public void deleteAllRatas() {
        repository.deleteAllRatas();
    }

    public LiveData<List<Rata>> getAllRatas() {
        return allRatas;
    }

    public void select(Rata rata) {
        selected.setValue(rata);
    }

    public LiveData<Rata> getSelected() {
        return selected;
    }

}
