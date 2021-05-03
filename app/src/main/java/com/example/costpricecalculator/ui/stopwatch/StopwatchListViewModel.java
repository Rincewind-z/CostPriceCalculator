package com.example.costpricecalculator.ui.stopwatch;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.costpricecalculator.model.DataBase;
import com.example.costpricecalculator.model.Stopwatch;
import com.example.costpricecalculator.model.StopwatchDao;

import java.util.List;

public class StopwatchListViewModel extends AndroidViewModel {

    private DataBase dataBase;
    private final StopwatchDao stopwatchDao;

    public LiveData<List<Stopwatch>> stopwatchLiveData;

    public StopwatchListViewModel(Application application) {
        super(application);

        dataBase = DataBase.get(application);
        stopwatchDao = dataBase.stopwatchDao();

        DataBase.databaseWriteExecutor.execute(() -> {
            stopwatchLiveData = stopwatchDao.getAll();
        });
    }

}