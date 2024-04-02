package ru.mirea.kharakhorinvd.lesson3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long dateInMillis = System.currentTimeMillis();
        String format = "yyyy-MM-dd HH:mm:ss";
        final SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateString = sdf.format(new Date(dateInMillis));
        EditText editText = findViewById(R.id.editTextText);
        editText.setText(dateString);


    }
    public void onClickSend(View view){
        Intent intent = new Intent(this,SecondActivity.class);

        EditText editText = findViewById(R.id.editTextText);
        String string = editText.getText().toString();
        intent.putExtra("first" , string );


        startActivity(intent);
    }
}