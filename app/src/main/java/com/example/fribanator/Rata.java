package com.example.fribanator;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

@Entity(tableName = "rata_table")
public class Rata {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String address;

    @ColumnInfo(name = "parList")
    private ArrayList<Integer> parList;

    public Rata(String name, String address, ArrayList<Integer> parList) {
        this.name = name;
        this.address = address;
        this.parList = parList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Integer> getParList() {return parList;}

    public void setParList(ArrayList<Integer> parList) {
        this.parList = parList;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

class ParListConverter {
    @TypeConverter
    public static ArrayList<Integer> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Integer> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }}
