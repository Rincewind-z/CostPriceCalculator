package com.example.costpricecalculator.ui.stopwatch.recyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.costpricecalculator.R;
import com.example.costpricecalculator.model.Stopwatch;

public class StopwatchViewHolder extends RecyclerView.ViewHolder {

    public TextView stopwatchName;
    public TextView stopwatchTime;
    public Stopwatch stopwatch;
    public NavController navController;

     public StopwatchViewHolder(@NonNull View itemView) {
        super(itemView);
        stopwatchName = itemView.findViewById(R.id.stopwatch_name);
        stopwatchTime = itemView.findViewById(R.id.stopwatch_time);
        itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("stopwatch", stopwatch);
            navController.navigate(R.id.action_createStopwatchDialog_to_stopwatchFragment, bundle);
        });
    }
}
