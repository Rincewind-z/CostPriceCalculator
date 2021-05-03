package com.example.costpricecalculator.ui.stopwatch.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.costpricecalculator.R;
import com.example.costpricecalculator.model.Stopwatch;

import java.util.List;

public class StopwatchAdapter extends RecyclerView.Adapter<StopwatchViewHolder> {

    public StopwatchAdapter(NavController navController) {
        this.navController = navController;
    }

    NavController navController;

    public void setStopwatchList(List<Stopwatch> stopwatchList) {
        this.stopwatchList = stopwatchList;
        notifyDataSetChanged();
    }

    private List<Stopwatch> stopwatchList;

    @NonNull
    @Override
    public StopwatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.stopwatch_list_item, parent, false);
        return new StopwatchViewHolder(view);
    }

    private String timeToString(long time){
        int h = (int)(time /3600000);
        int m = (int)(time - h*3600000)/60000;
        int s = (int)(time - h*3600000- m*60000)/1000 ;
        return (h < 10 ? "0"+h: h)+":"+(m < 10 ? "0"+m: m)+":"+ (s < 10 ? "0"+s: s);
    }

    @Override
    public void onBindViewHolder(@NonNull StopwatchViewHolder holder, int position) {
        if (stopwatchList == null) {
            holder.stopwatchName.setText("хй");
            holder.stopwatchTime.setText("пзд");
        }
        else {
            Stopwatch stopwatch = stopwatchList.get(position);
            holder.stopwatchName.setText(stopwatch.getStopwatch_name());
            holder.stopwatchTime.setText(timeToString(stopwatch.getTime()));
            holder.stopwatch = stopwatch;
            holder.navController = navController;
        }
    }

    @Override
    public int getItemCount() {
        if (stopwatchList == null) {
            return 0;
        }
        return stopwatchList.size();
    }
}
