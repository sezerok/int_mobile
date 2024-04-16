package ru.mirea.kharakhorinvd.lesson4;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.mirea.kharakhorinvd.lesson4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private	ActivityMainBinding	binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding	= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.editTextMirea.setText("Мой	номер	по	списку	№28");
        binding.buttonMirea.setOnClickListener(new View.OnClickListener()	{
            @Override
            public void onClick(View view)	{
                Log.d(MainActivity.class.getSimpleName(),"onClickListener");
            }
        });
    }

}