package com.example.costpricecalculator.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StopwatchDao {
    @Query("Select * from STOPWATCH")
    LiveData<List<Stopwatch>> getAll();

    @Query("Select * from stopwatch where _id = :id")
    Stopwatch getById(long id);

    @Insert
    long insert(Stopwatch stopwatch);

    @Update
    void update(Stopwatch stopwatch);

    @Delete
    void delete(Stopwatch stopwatch);
}
