package ru.mirea.kharakhorinvd.looper;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Handler;
import java.util.logging.LogRecord;

import ru.mirea.kharakhorinvd.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.agebutton.setText("50");
        binding.proffbutton.setText("govnochist");
        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {


            public void handleMessage(Message msg) {

                Log.d(MainActivity.class.getSimpleName(), "Task execute. This is result: " + msg.getData().getString("re- sult"));

            }
        };
        MyLooper myLooper = new MyLooper(mainThreadHandler);
        myLooper.start();
        //binding.editTextMirea.setText("Мой номер по списку No28");

        binding.buttonMirea.setOnClickListener(new View.OnClickListener()	{
            @Override
            public void onClick(View view)	{
                int age = Integer.parseInt(binding.agebutton.getText().toString());

                String proff = binding.proffbutton.getText().toString();
                int delayMillis = age * 1000;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(delayMillis);
                            Message message = Message.obtain();
                            Bundle bundle = new Bundle();
                            bundle.putString("result", proff + age);
                            message.setData(bundle);
                            //mainThreadHandler.sendMessage(message);
                            myLooper.mHandler.sendMessage(message);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

//                Message msg = Message.obtain();
//                Bundle bundle = new Bundle();
//                bundle.putString("KEY", "mirea");
//                msg.setData(bundle);
//                myLooper.mHandler.sendMessage(msg);
            }
        });
        }
}