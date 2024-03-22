package ru.mirea.kharakhorinvd.dialog;

import android.view.View;
import com.google.android.material.snackbar.Snackbar;


public class MySnackbar {
    public void showSnackbar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
}
