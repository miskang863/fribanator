package com.example.fribanator;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ScoreDao {

    @Insert
    void insert(Score score);

    @Query("SELECT * FROM score_table ORDER BY rataName ASC")
    LiveData<List<Score>> getAllScores();

//    @Update
  //  void update(Rata rata);
    //@Query("SELECT * FROM currencies")
}