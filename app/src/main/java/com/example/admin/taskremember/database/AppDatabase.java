package com.example.admin.taskremember.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.admin.taskremember.database.Task;
import com.example.admin.taskremember.database.TaskDao;

@Database(entities = {Task.class},version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

}
