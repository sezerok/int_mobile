package ru.mirea.kharakhorinvd.timeservice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import ru.mirea.kharakhorinvd.timeservice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final String host = "time.nist.gov"; // или time-a.nist.gov
    private final int port = 13;
    private static final String TAG = "TimeService"; // Добавлено для логирования

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetTimeTask timeTask = new GetTimeTask();
                timeTask.execute();
            }
        });
    }

    private class GetTimeTask extends AsyncTask<Void, Void, String[]> {
        @Override
        protected String[] doInBackground(Void... params) {
            String[] mas = new String[2];
            try {
                Socket socket = new Socket(host, port);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                reader.readLine();
                String timeResult = reader.readLine(); // считываем вторую строку
                Log.d(TAG, "Time: " + timeResult);
                mas[0] = timeResult.split(" ")[1]; // пример разбора строки для получения времени
                mas[1] = timeResult.split(" ")[2]; // пример разбора строки для получения даты
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return mas;
        }

        @Override
        protected void onPostExecute(String[] result) {
            super.onPostExecute(result);
            if (result != null && result.length == 2) {
                binding.dataView.setText(result[0]);
                binding.timeView.setText(result[1]);
            } else {
                binding.dataView.setText("Error");
                binding.timeView.setText("Error");
            }
        }
    }
}