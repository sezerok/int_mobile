package ru.mirea.kharakhorinvd.multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");
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
    public void onClickNewActivity (View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
    public void onClickSend(View view){
        EditText editText = findViewById(R.id.editTextText);
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("send", editText.getText().toString());
        startActivity(intent);
    }
}