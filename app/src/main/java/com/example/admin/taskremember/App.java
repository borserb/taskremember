package com.example.admin.taskremember;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.admin.taskremember.database.AppDatabase;

public class App extends Application {

    private AppDatabase db;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppDatabase getDb() {
        if (db == null) {
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "databse-name").allowMainThreadQueries().build();
        }
        return db;
    }


}
