package com.example.costpricecalculator.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "stopwatch")
public class Stopwatch {

    @PrimaryKey
    @NonNull
    int _id;
    String stopwatch_name;
    String time;

    public Stopwatch (int _id, String stopwatch_name, String time) {
        this._id = _id;
        this.stopwatch_name = stopwatch_name;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Stopwatch{" +
                "_id=" + _id +
                ", stopwatch_name='" + stopwatch_name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
