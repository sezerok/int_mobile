package ru.mirea.kharakhorinvd.httpurlconnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.mirea.kharakhorinvd.httpurlconnection.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConnectivityManager connectivityManager =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkinfo = null;
                if (connectivityManager != null) {
                    networkinfo = connectivityManager.getActiveNetworkInfo();
                }
                if (networkinfo != null && networkinfo.isConnected()) {
                    new DownloadPageTask().execute("https://ipinfo.io/json");
                } else {
                    Toast.makeText(MainActivity.this, "Нет интернета", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class DownloadPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.textView.setText("Загружаем...");
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            binding.textView.setText(result);
            Log.d(TAG, result);
            try {
                JSONObject responseJson = new JSONObject(result);
                Log.d(TAG, "Response: " + responseJson);
                String ip = responseJson.getString("ip");
                Log.d(TAG, "IP: " + ip);

                String loc = responseJson.getString("loc");
                String[] coords = loc.split(",");
                float latitude = Float.parseFloat(coords[0]);
                float longitude = Float.parseFloat(coords[1]);
                binding.textView3.setText("city - "+responseJson.getString("city"));
                binding.textView4.setText("region - "+responseJson.getString("region"));
                new RequestWeatherInfo(latitude, longitude).execute();

            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
    }

    private String downloadIpInfo(String address) throws IOException {
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read;
                while ((read = inputStream.read()) != -1) {
                    bos.write(read);
                }
                bos.close();
                data = bos.toString();
            } else {
                data = connection.getResponseMessage() + ". Error Code: " + responseCode;
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }

    private class RequestWeatherInfo extends BaseHttpRequestTask {
        private static final String apiKey = "ae53760321911b952e3646887ead8c6d";
        public RequestWeatherInfo(float latitude, float longitude) {
            super(String.format("https://api.openweathermap.org/data/2.5/weather?lat=%.4f&lon=%.4f&appid=%s",
                    latitude, longitude, apiKey
            ), "GET");
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject responseJson = new JSONObject(result);
                binding.textView.setText("weather - "+responseJson.getJSONArray("weather").getJSONObject(0).getString("main"));
                binding.textView2.setText("temp - "+String.format("%.2f", responseJson.getJSONObject("main").getDouble("temp") - 273.15));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private abstract class BaseHttpRequestTask extends AsyncTask<Void, Void, String> {
        private final String address;
        private final String method;
        public BaseHttpRequestTask(String address, String method) {
            this.address = address;
            this.method = method;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                return MakeRequest();
            } catch (IOException | RuntimeException e) {
                return null;
            }
        }
        private String MakeRequest() throws IOException, RuntimeException {
            final URL url = new URL(address);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod(method);
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
                throw new RuntimeException("Invalid return code");

            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            for (int read = 0; (read = inputStream.read()) != -1;) {
                bos.write(read);
            }
            final String result = bos.toString();
            connection.disconnect();
            bos.close();
            return result;
        }
    }
}