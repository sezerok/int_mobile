package ru.mirea.kharakhorinvd.internalfilestorage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileOutputStream;

import ru.mirea.kharakhorinvd.internalfilestorage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    //private EditText tv;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private String fileName = "importantdaterussia.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EditText tv = binding.editTextText;
        tv.setText("988 god - kreshenie rusi");
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = tv.getText().toString();
                FileOutputStream outputStream;
                try {
                    outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                    outputStream.write(string.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}