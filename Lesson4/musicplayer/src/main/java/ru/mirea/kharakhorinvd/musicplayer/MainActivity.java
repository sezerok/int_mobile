package ru.mirea.kharakhorinvd.musicplayer;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.mirea.kharakhorinvd.musicplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    MediaPlayer music = new MediaPlayer();
    private ActivityMainBinding binding;

    boolean Flag_sp = true;
    int number_m = 0;
    ArrayList<Integer> fileList = new ArrayList<>();
    int m = R.raw.toidream;
    int m1 = R.raw.roym;
    int m2 = R.raw.burnout;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fileList.add(m);
        fileList.add(m1);
        fileList.add(m2);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textView.setText("музыка");
        music = MediaPlayer.create(MainActivity.this, fileList.get(number_m));
        binding.textView.setText(fileList.get(number_m));
        binding.startstop.setOnClickListener(new View.OnClickListener()	{
            @Override
            public void onClick(View view)	{

                if (Flag_sp == true){
                    binding.startstop.setImageResource(R.drawable.ic_pause);
                    Flag_sp = false;
                    music.start();
                }
                else {
                    binding.startstop.setImageResource(R.drawable.ic_start);
                    Flag_sp = true;
                    music.pause();
                }
            }
        });
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number_m + 1 < fileList.size()){

                    number_m += 1;
                    music.reset();
                    binding.textView.setText(fileList.get(number_m));
                    music = MediaPlayer.create(MainActivity.this, fileList.get(number_m));
                    music.start();
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),"треков больше нет", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number_m - 1 >= 0){

                    number_m -= 1;
                    music.reset();
                    binding.textView.setText(fileList.get(number_m));
                    music = MediaPlayer.create(MainActivity.this, fileList.get(number_m));
                    music.start();
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),"треков больше нет", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });


        }
    }

