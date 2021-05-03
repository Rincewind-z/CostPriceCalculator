package com.example.costpricecalculator.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "stopwatch")
public class Stopwatch  implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    long _id;
    String stopwatch_name;
    long time;

    public Stopwatch(int _id, String stopwatch_name, long time) {
        this._id = _id;
        this.stopwatch_name = stopwatch_name;
        this.time = time;
    }

    public Stopwatch() {}

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getStopwatch_name() {
        return stopwatch_name;
    }

    public void setStopwatch_name(String stopwatch_name) {
        this.stopwatch_name = stopwatch_name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
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
