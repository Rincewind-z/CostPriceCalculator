package com.example.costpricecalculator.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Stopwatch.class}, version = 5)
public abstract class DataBase extends RoomDatabase {
    public abstract StopwatchDao stopwatchDao();

    private static final int NUMBER_OF_THREADS = 4;
    static public final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    private static final String db_name = "cpc.db";
    private static volatile DataBase instance = null;

    public synchronized static DataBase get(Context context) {
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
