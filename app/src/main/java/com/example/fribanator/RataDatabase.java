package com.example.fribanator;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


@Database(entities = {Rata.class, Score.class}, version = 1)
@TypeConverters(ParListConverter.class)
public abstract class RataDatabase extends RoomDatabase {

    private static RataDatabase instance;

    public abstract RataDao rataDao();
    public abstract ScoreDao scoreDao();


    public static synchronized RataDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RataDatabase.class, "rata_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private RataDao rataDao;
        private ScoreDao scoreDao;

        private PopulateDbAsyncTask(RataDatabase db) {

            rataDao = db.rataDao();
            scoreDao = db.scoreDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<Integer> myPars1 = new ArrayList<>(Arrays.asList(3,3,4,3,3,4,2,2,3));
            ArrayList<Integer> myPars2 = new ArrayList<>(Arrays.asList(3,3,4,3,3,4,2,2,3));
            ArrayList<Integer> myPars3 = new ArrayList<>(Arrays.asList(3,3,4,3,3,4,2,2,3,3,3,4,3,3,4,2,2,3));

            rataDao.insert(new Rata("Siltamäen Frisbeegolfrata", "Kukkopolku 1", myPars1));
            rataDao.insert(new Rata("Ford Frisbeegolf", "Kuusitie 3", myPars2));
            rataDao.insert(new Rata("Kellokoski DiscGolfPark", "Kellokuja 666", myPars3));
            rataDao.insert(new Rata("Kivikon Frisbeegolfrata", "Tie 1", myPars1));
            rataDao.insert(new Rata("Talma Frisbeegolfrata", "Katu 5", myPars2));
            rataDao.insert(new Rata("Sibbe DiscGolf", "Nakkivene 32", myPars3));
            rataDao.insert(new Rata("Nurmijärven Frisbeegolfrata", "Kuntokuja 69", myPars2));
            rataDao.insert(new Rata("Lakisto Frisbeepark", "Lakisto 43", myPars3));

            scoreDao.insert(new Score("Siltamäen Frisbeegolfrata", myPars1));

            return null;
        }
    }

}
