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


@Database(entities = Rata.class, version = 1)
@TypeConverters(ParListConverter.class)
public abstract class RataDatabase extends RoomDatabase {

    private static RataDatabase instance;

    public abstract RataDao rataDao();

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

        private PopulateDbAsyncTask(RataDatabase db) {
            rataDao = db.rataDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
           // getCurrencies();
            ArrayList<Integer> myPars1 = new ArrayList<>(Arrays.asList(3,3,4,3,3,4,2,2,3));
            ArrayList<Integer> myPars2 = new ArrayList<>(Arrays.asList(3,3,4,3,3,4,2,2,3));
            ArrayList<Integer> myPars3 = new ArrayList<>(Arrays.asList(3,3,4,3,3,4,2,2,3,3,3,4,3,3,4,2,2,3));



            rataDao.insert(new Rata("Siltamäki", "Kukkopolku 1", myPars1));
            rataDao.insert(new Rata("Ford Frisbeegolf", "Kuusitie 3", myPars2));
            rataDao.insert(new Rata("Kellokoski DiscGolPark", "", myPars3));
            rataDao.insert(new Rata("Kivikon frisbeegolfrata", "Tie 1", myPars1));
            rataDao.insert(new Rata("Talmna frisbeegolfrata", "Katu 5", myPars2));
            rataDao.insert(new Rata("Sibbe Disc Golf", "Nakkivene 32", myPars3));
            rataDao.insert(new Rata("Nurmijärven frisbeegolfrata", "Kuntokuja 69", myPars2));
            rataDao.insert(new Rata("Lakisto Frisbeepark", "Lakisto 43", myPars3));


            return null;
        }
/*
        void getCurrencies() {
            ArrayList<Rata> currencies = new ArrayList<>();
            final OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml")
                    .build();

            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d("demo", "onResponse: " + Thread.currentThread().getId());

                    if (response.isSuccessful()) {
                        ResponseBody responseBody = response.body();
                        CurrencyXmlParser currencyXmlParser = new CurrencyXmlParser();
                        try {
                            ArrayList<Rata> currencies = currencyXmlParser.parse(response.body().byteStream());
                            currencies.forEach(currency -> {
                                rataDao.insert(currency);
                            });
                        } catch (SAXException e) {
                            e.printStackTrace();
                        }
                    } else {
                        ResponseBody responseBody = response.body();
                        String body = responseBody.string();
                        Log.d("demo", "onResponse: " + body);
                    }
                }
            });

        }
*/
    }

}
