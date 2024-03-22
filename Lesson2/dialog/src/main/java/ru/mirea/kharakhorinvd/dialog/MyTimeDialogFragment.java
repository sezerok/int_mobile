package ru.mirea.kharakhorinvd.dialog;
import android.animation.TimeAnimator;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.sql.Date;
import java.sql.Time;

public class MyTimeDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return new TimePickerDialog(getActivity(),this,0,0,true);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(getActivity(),"Вы выбрали время: "+hourOfDay+":"+minute,Toast.LENGTH_SHORT).show();
    }
}
