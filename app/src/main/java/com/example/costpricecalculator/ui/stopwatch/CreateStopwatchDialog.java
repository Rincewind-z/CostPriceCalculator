package com.example.costpricecalculator.ui.stopwatch;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.costpricecalculator.MainActivity;
import com.example.costpricecalculator.R;
import com.example.costpricecalculator.model.DataBase;
import com.example.costpricecalculator.model.Stopwatch;

import java.util.List;

public class CreateStopwatchDialog extends AppCompatDialogFragment {
    private EditText editTextStopwatchName;
    private DataBase dataBase;
    private final DialogCallback dialogCallback;

    public CreateStopwatchDialog(DialogCallback dialogCallback){
        this.dialogCallback = dialogCallback;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.stopwatch_name_dialog, null);

        dataBase = DataBase.get(view.getContext());

        builder.setView(view)
                .setTitle("Создать")
                .setNegativeButton("Отмена", (dialog, which) -> {})
                .setPositiveButton("Сохранить", (dialog, which) -> {
                    String stopwatchName = editTextStopwatchName.getText().toString();
                    createStopwatch(stopwatchName);
                });

        editTextStopwatchName = view.findViewById(R.id.edit_stopwatch_name);

        return builder.create();
    }

    @SuppressLint("StaticFieldLeak")
    public void createStopwatch (String name) {
        new AsyncTask<String, Void, Stopwatch>() {
            @Override
            protected Stopwatch doInBackground(String... stopwatchName) {
                Stopwatch stopwatch = new Stopwatch();
                stopwatch.setStopwatch_name(name);
                stopwatch.set_id(dataBase.stopwatchDao().insert(stopwatch));
                onProgressUpdate();
                return stopwatch;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(Stopwatch stopwatch) {
                dialogCallback.onDialogSuccess(stopwatch);
            }
        }.execute(name);
    }

    public interface DialogCallback{
        void onDialogSuccess(Stopwatch stopwatch);
    }

}
