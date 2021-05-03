package com.example.costpricecalculator.ui.stopwatch;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.costpricecalculator.R;
import com.example.costpricecalculator.model.DataBase;
import com.example.costpricecalculator.model.Stopwatch;

public class StopwatchFragment extends Fragment {

    private StopwatchViewModel mViewModel;
    private TextView textViewName;
    private Chronometer mChronometer;
    private Button start_button;
    private Button stop_button;
    private Button reset_button;
    private long timeWhenStopped = 0;
    private Stopwatch stopwatch;
    private DataBase dataBase;

    private String timeToString(long time){
        int h = (int)(time /3600000);
        int m = (int)(time - h*3600000)/60000;
        int s = (int)(time - h*3600000- m*60000)/1000 ;
        return (h < 10 ? "0"+h: h)+":"+(m < 10 ? "0"+m: m)+":"+ (s < 10 ? "0"+s: s);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        textViewName = view.findViewById(R.id.stopwatch_name);

        mChronometer = view.findViewById(R.id.chronometer);

        start_button = view.findViewById(R.id.start_button);
        stop_button = view.findViewById(R.id.stop_button);
        reset_button = view.findViewById(R.id.reset_button);

        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            public void onChronometerTick(Chronometer cArg) {
                long time = SystemClock.elapsedRealtime() - cArg.getBase();
                cArg.setText(timeToString(time));
            }
        });

        stopwatch = (Stopwatch) getArguments().getSerializable("stopwatch");

        timeWhenStopped =  - stopwatch.getTime();
        textViewName.setText(stopwatch.getStopwatch_name());

        //mChronometer.setBase(SystemClock.elapsedRealtime() - timeWhenStopped);
        mChronometer.setText(timeToString(-timeWhenStopped));

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Секундомер");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StopwatchViewModel.class);


        dataBase = DataBase.get(getContext());

        start_button.setOnClickListener(v -> onStartClick(v));

        stop_button.setOnClickListener(v -> onStopClick(v));

        reset_button.setOnClickListener(v -> onResetClick(v));
        // TODO: Use the ViewModel
    }

    public void onStartClick(View view) {
        mChronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
        mChronometer.start();

        start_button.setEnabled(false);
    }

    @SuppressLint("StaticFieldLeak")
    public void onStopClick(View view) {
        timeWhenStopped = SystemClock.elapsedRealtime() - mChronometer.getBase();
        mChronometer.stop();

        start_button.setEnabled(true);

        new AsyncTask<Void, Void, Stopwatch>() {
            @Override
            protected Stopwatch doInBackground(Void... stopwatches) {
                stopwatch.setTime(timeWhenStopped);
                dataBase.stopwatchDao().update(stopwatch);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void onResetClick(View view) {
        mChronometer.setBase(SystemClock.elapsedRealtime());
        timeWhenStopped = 0;
        mChronometer.stop();
        mChronometer.setText("00:00:00");

        start_button.setEnabled(true);

        new AsyncTask<Void, Void, Stopwatch>() {
            @Override
            protected Stopwatch doInBackground(Void... stopwatches) {
                stopwatch.setTime(timeWhenStopped);
                dataBase.stopwatchDao().update(stopwatch);
                return null;
            }
        }.execute();
    }
}
