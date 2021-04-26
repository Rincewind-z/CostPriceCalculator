package com.example.costpricecalculator.ui.stopwatch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StopwatchListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public StopwatchListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is stopwatch fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}