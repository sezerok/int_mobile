package ru.mirea.kharakhorinvd.multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
        setContentView(R.layout.activity_second);
        String text = (String) getIntent().getSerializableExtra("send");
        TextView textView = findViewById(R.id.textView);
        textView.setText(text);
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG,"onStart");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG,"onResume");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG,"onPause");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }
}