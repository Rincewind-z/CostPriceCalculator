package com.example.costpricecalculator.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "stopwatch")
public class Stopwatch {

    @PrimaryKey
    @NonNull
    int stopwatch_id;
    String stopwatch_name;
    String time;

    public Stopwatch (int stopwatch_id, String stopwatch_name, String time) {
        this.stopwatch_id = stopwatch_id;
        this.stopwatch_name = stopwatch_name;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Stopwatch{" +
                "stopwatch_id=" + stopwatch_id +
                ", stopwatch_name='" + stopwatch_name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
