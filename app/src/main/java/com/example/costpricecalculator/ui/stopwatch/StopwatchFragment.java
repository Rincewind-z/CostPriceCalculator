package com.example.costpricecalculator.ui.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.SystemClock;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.costpricecalculator.R;

public class StopwatchFragment extends Fragment {

    private StopwatchViewModel mViewModel;
    private TextView textViewName;
    private Chronometer mChronometer;
    private Button start_button;
    private Button stop_button;
    private Button reset_button;
    private long timeWhenStopped = 0;

    public static StopwatchFragment newInstance() {
        return new StopwatchFragment();
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
                int h   = (int)(time /3600000);
                int m = (int)(time - h*3600000)/60000;
                int s= (int)(time - h*3600000- m*60000)/1000 ;
                String t = (h < 10 ? "0"+h: h)+":"+(m < 10 ? "0"+m: m)+":"+ (s < 10 ? "0"+s: s);
                cArg.setText(t);
            }
        });

        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.setText("00:00:00");

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Секундомер");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StopwatchViewModel.class);
        textViewName.setText(getArguments().getString("name"));

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

    public void onStopClick(View view) {
        timeWhenStopped = mChronometer.getBase() - SystemClock.elapsedRealtime();
        mChronometer.stop();

        start_button.setEnabled(true);
    }

    public void onResetClick(View view) {
        mChronometer.setBase(SystemClock.elapsedRealtime());
        timeWhenStopped = 0;
        mChronometer.stop();
        mChronometer.setText("00:00:00");

        start_button.setEnabled(true);
    }
}
