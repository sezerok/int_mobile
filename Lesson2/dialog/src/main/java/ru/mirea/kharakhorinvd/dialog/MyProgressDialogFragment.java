package ru.mirea.kharakhorinvd.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyProgressDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ProgressBar progressBar = new ProgressBar(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Успех, жди меня!")
                .setView(progressBar);
        return builder.create();
    }
}
