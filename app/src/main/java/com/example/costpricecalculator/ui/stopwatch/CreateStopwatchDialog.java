package com.example.costpricecalculator.ui.stopwatch;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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

public class CreateStopwatchDialog extends AppCompatDialogFragment {
    private EditText editTextStopwatchName;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.stopwatch_name_dialog, null);

        builder.setView(view)
                .setTitle("Создать")
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String stopwatchName = editTextStopwatchName.getText().toString();
                        Bundle bundle = new Bundle();
                        bundle.putString("name", stopwatchName);

                        NavController navController = NavHostFragment.findNavController(CreateStopwatchDialog.this);
                        //if (navController.getCurrentDestination().getId() == R.id.createStopwatchDialog) {
                            navController.navigate(R.id.action_createStopwatchDialog_to_stopwatchFragment, bundle);
                        //}

                    }
                });

        editTextStopwatchName = view.findViewById(R.id.edit_stopwatch_name);

        return builder.create();
    }

}
