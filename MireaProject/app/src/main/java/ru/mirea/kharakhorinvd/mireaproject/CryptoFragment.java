package ru.mirea.kharakhorinvd.mireaproject;



import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CryptoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CryptoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CryptoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CryptoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CryptoFragment newInstance(String param1, String param2) {
        CryptoFragment fragment = new CryptoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crypto, container, false);

        // Найдите представления по их идентификаторам
        EditText fileNameEditText = view.findViewById(R.id.editTextText101);
        EditText quoteEditText = view.findViewById(R.id.editTextText102);
        Button saveButton = view.findViewById(R.id.buttoncryptosave);
//        Button loadButton = view.findViewById(R.id.button102);
//        TextView textView = view.findViewById(R.id.textView101);
        TextView textView = view.findViewById(R.id.textView101);
        Button loadButton = view.findViewById(R.id.buttoncryptoload);
        // Установите обработчики нажатия для кнопок
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExternalStorageWritable()){
                    writeFileToExternalStorage(fileNameEditText.getText().toString(),crypt(quoteEditText.getText().toString()));
                }
                else {
                    textView.setText("error write");
                }
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExternalStorageReadable()){
                    textView.setText(readFileFromExternalStorage(fileNameEditText.getText().toString()));
                }
                else {
                    textView.setText("error read");
                }
            }
        });

        return view;
    }
    public String crypt(String string){
        char[] crypt = string.toCharArray();
        for (int i = 0; i < crypt.length; i++) {
            char c = crypt[i];
            int cex = Character.getNumericValue(c)+1;
            char cf = (char) cex;
            crypt[i]=cf;
        }
        String fin = crypt.toString();
        return fin;
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