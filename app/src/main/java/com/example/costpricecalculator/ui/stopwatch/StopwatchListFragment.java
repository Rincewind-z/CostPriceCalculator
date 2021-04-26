package com.example.costpricecalculator.ui.stopwatch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.costpricecalculator.R;

public class StopwatchListFragment extends Fragment {

    private StopwatchListViewModel stopwatchViewModel;
    private Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        stopwatchViewModel =
                new ViewModelProvider(this).get(StopwatchListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_stopwatch_list, container, false);

        button = root.findViewById(R.id.go_to_stopwatch);

        button.setOnClickListener(v -> openDialog());

        /*button.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(StopwatchListFragment.this);
            navController.navigate(R.id.action_navigation_stopwatch_to_stopwatchFragment);
        });*/
        return root;
    }

    public void openDialog() {
        CreateStopwatchDialog createStopwatchDialog = new CreateStopwatchDialog();
        createStopwatchDialog.show(getChildFragmentManager(), "create stopwatch name");
    }

}