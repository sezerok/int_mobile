package ru.mirea.kharakhorinvd.lesson6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.mirea.kharakhorinvd.lesson6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final String PREFS_FILE = "MyPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String group = binding.editTextText.getText().toString();
                int number = Integer.parseInt(binding.editTextText2.getText().toString());
                String favFilm = binding.editTextText3.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("groupNumber", group);
                editor.putInt("listNumber", number);
                editor.putString("favoriteMovie", favFilm);
                editor.apply();            }
        });

    }
}