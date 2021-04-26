package com.example.costpricecalculator.ui.material;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MaterialViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MaterialViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is material fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}