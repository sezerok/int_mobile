package ru.mirea.kharakhorinvd.notebook;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import kotlin.text.Charsets;
import ru.mirea.kharakhorinvd.notebook.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    EditText fileNameEditText, quoteEditText;
    Button saveButton, loadButton;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fileNameEditText = binding.editTextText;
        quoteEditText = binding.editTextText2;
        saveButton = binding.button;

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExternalStorageWritable()){
                    writeFileToExternalStorage(fileNameEditText.getText().toString(),quoteEditText.getText().toString());
                }
                else {
                    binding.textView.setText("error write");
                }
            }
        });

        loadButton = binding.button2;
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExternalStorageReadable()){
                    binding.textView.setText(readFileFromExternalStorage(fileNameEditText.getText().toString()));
                }
                else {
                    binding.textView.setText("error read");
                }
            }
        });

    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    /* Проверяем внешнее хранилище на доступность чтения */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;}
    public void writeFileToExternalStorage(String fileName, String quote) {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, fileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
            OutputStreamWriter output = new OutputStreamWriter(fileOutputStream);
// Запись строки в файл
            output.write(quote);
// Закрытие потока записи
            output.close();
        } catch (IOException e) {
            Log.w("ExternalStorage", "Error writing " + file, e);
        }
    }
    public String readFileFromExternalStorage(String fileName) {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);

            List<String> lines = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            return lines.toString();
            //Log.w("ExternalStorage", String.format("Read from file %s successful", lines.toString()));
        } catch (Exception e) {
            return "error";
            //Log.w("ExternalStorage", String.format("Read from file %s failed", e.getMessage()));
        }
    }
}
