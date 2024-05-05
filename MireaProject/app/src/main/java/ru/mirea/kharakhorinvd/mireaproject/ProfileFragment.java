package ru.mirea.kharakhorinvd.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String PREFS_FILE = "MyPrefs";
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Находим все необходимые представления по их ID
        EditText editTextText = view.findViewById(R.id.editTextText101);
        EditText editTextText2 = view.findViewById(R.id.editTextText102);
        EditText editTextText3 = view.findViewById(R.id.editTextText3);
        Button button = view.findViewById(R.id.buttoncryptosave);

        // Настройка обработчика нажатия кнопки
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получение значений из EditText и сохранение их в SharedPreferences
                String firstString = editTextText.getText().toString();
                int secondString = Integer.parseInt(editTextText2.getText().toString());
                String thirdString = editTextText3.getText().toString();
                SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("1", firstString);
                editor.putInt("2", secondString);
                editor.putString("3", thirdString);
                editor.apply();
            }
        });

        return view;
    }

}