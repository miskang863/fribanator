package com.example.fribanator;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "score_table")
public class Score {

    @PrimaryKey(autoGenerate = true)
    public int scoreId;

    public String rataName;

    @ColumnInfo(name = "scoreList")
    private ArrayList<Integer> scoreList;


    private String date;

    public Score(String rataName, ArrayList<Integer> scoreList, String date) {
        this.rataName = rataName;
        this.scoreList = scoreList;
        this.date = date;
    }

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public String getRataName() {
        return rataName;
    }

    public void setRataName(String rataName) {
        this.rataName = rataName;
    }

    public ArrayList<Integer> getScoreList() {
        return scoreList;
    }

    public void setScoreList(ArrayList<Integer> scoreList) {
        this.scoreList = scoreList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
