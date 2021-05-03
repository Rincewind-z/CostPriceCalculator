package com.example.costpricecalculator.ui.stopwatch;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.costpricecalculator.R;
import com.example.costpricecalculator.model.DataBase;
import com.example.costpricecalculator.model.Stopwatch;
import com.example.costpricecalculator.ui.stopwatch.recyclerView.StopwatchAdapter;

public class StopwatchListFragment extends Fragment {

    DataBase dataBase;

    private StopwatchListViewModel stopwatchViewModel;
    private Button button;
    private NavController navController;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        stopwatchViewModel =
                new ViewModelProvider(this).get(StopwatchListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_stopwatch_list, container, false);

        navController = NavHostFragment.findNavController(StopwatchListFragment.this);


        StopwatchAdapter stopwatchAdapter = new StopwatchAdapter(navController);
        recyclerView = root.findViewById(R.id.stopwatch_list);
        recyclerView.setAdapter(stopwatchAdapter);

        stopwatchViewModel.stopwatchLiveData.observe(getViewLifecycleOwner(), stopwatches -> {
            if (stopwatches != null) {
                stopwatchAdapter.setStopwatchList(stopwatches);
            }
        });

        button = root.findViewById(R.id.go_to_stopwatch);

        button.setOnClickListener(v -> openDialog());


        return root;
    }

    public void openDialog() {
        CreateStopwatchDialog createStopwatchDialog = new CreateStopwatchDialog(stopwatch -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("stopwatch", stopwatch);
            Log.d("DATABASE", stopwatch.toString());
            navController.navigate(R.id.action_createStopwatchDialog_to_stopwatchFragment, bundle);
        });
        createStopwatchDialog.show(getChildFragmentManager(), "create stopwatch name");
    }

}