package ru.mirea.kharakhorinvd.activitylifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();


    private void showLog(String nameLog){
        EditText editText = findViewById(R.id.editTextText2);
        editText.setText(nameLog);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
        setContentView(R.layout.activity_main);
        showLog("onCreate");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG,"onStart");
        showLog("onStart");
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        Log.i(TAG,"onRestoreInstanceState");
        showLog("onRestoreInstanceState");
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i(TAG, "onPostCreate");
        showLog("onPostCreate");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG,"onRestart");
        showLog("onRestart");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG,"onResume");
        showLog("onResume");
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG, "onPostResume");
        showLog("onPostResume");
    }
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow");
        showLog("onAttachedToWindow");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG,"onPause");
        showLog("onPause");
    }
    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        showLog("onSaveInstanceState");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG,"onStop");
        showLog("onStop");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG,"onDestroy");
        showLog("onDestroy");
    }
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow");
        showLog("onDetachedFromWindow");
    }
}