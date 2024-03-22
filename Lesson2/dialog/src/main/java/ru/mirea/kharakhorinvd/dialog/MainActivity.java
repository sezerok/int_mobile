package ru.mirea.kharakhorinvd.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickShowDialog(View view) {
        String str = "mirea";
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),str);
    }
    public void onClickShowSnackBar(View view){
        MySnackbar snackbar = new MySnackbar();
        snackbar.showSnackbar(view,"it's snackbar");
    }
    public void onClickShowTimeDialog(View view){
        MyTimeDialogFragment myTimeDialogFragment = new MyTimeDialogFragment();
        myTimeDialogFragment.show(getSupportFragmentManager(),"time");
    }
    public void onClickShowDateDialog(View view){
        MyDateDialogFragment myDateDialogFragment = new MyDateDialogFragment();
        myDateDialogFragment.show(getSupportFragmentManager(),"date");

    }
    public void onClickShowProgressDialog(View view){
        MyProgressDialogFragment myProgressDialogFragment = new MyProgressDialogFragment();
        myProgressDialogFragment.show(getSupportFragmentManager(),"прогресс");
    }
    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",
                Toast.LENGTH_LONG).show();
    }
}