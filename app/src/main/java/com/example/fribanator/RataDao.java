package com.example.fribanator;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RataDao {

    @Insert
    void insert(Rata rata);

    @Update
    void update(Rata rata);

    @Delete
    void delete(Rata rata);

    @Query("DELETE FROM rata_table")
    void deleteAllRatas();

    @Query("SELECT * FROM rata_table ORDER BY name ASC")
    LiveData<List<Rata>> getAllRatas();

    //@Query("SELECT * FROM currencies")

}
