package com.example.costpricecalculator.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Stopwatch.class}, version = 2)
public abstract class DataBase extends RoomDatabase {
    abstract StopwatchDao stopwatchDao();

    private static final String db_name = "cpc.db";
    private static volatile DataBase instance = null;

    synchronized static DataBase get(Context context) {
        if (instance == null) {
            instance = create(context, false);
        }
        return instance;
    }

    public static DataBase create (Context context, boolean memoryOnly) {
        RoomDatabase.Builder<DataBase> dataBaseBuilder;
        if (memoryOnly) {
            dataBaseBuilder = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                    DataBase.class);
        }
        else {
            dataBaseBuilder = Room.databaseBuilder(context.getApplicationContext(),
                    DataBase.class,
                    db_name);
        }
        dataBaseBuilder.fallbackToDestructiveMigration();
        return (dataBaseBuilder.build());
    }
}
