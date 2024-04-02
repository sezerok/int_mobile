package ru.mirea.kharakhorinvd.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataFragment newInstance(String param1, String param2) {
        DataFragment fragment = new DataFragment();
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
        View root = inflater.inflate(R.layout.fragment_data, container, false);
        TextView textView = root.findViewById(R.id.textView2);
        textView.setText("Мобильные приложения: персонализированный и удобный доступ к вашему цифровому миру.\n" +
                "\n" +
                "В современном мире мобильные приложения стали неотъемлемой частью нашей повседневной жизни. Они предоставляют нам возможность связаться с друзьями, получить доступ к информации и развлечениям, совершать покупки и управлять нашими делами прямо с наших мобильных устройств.\n" +
                "\n" +
                "Простота и удобство использования: Мобильные приложения в стиле \"Material You\" разработаны с акцентом на удобство использования и интуитивно понятный интерфейс. Они предлагают элегантный и легко доступный способ выполнения задач на вашем смартфоне или планшете.\n" +
                "\n" +
                "Персонализация и адаптация под вас: В мире \"Material You\" мобильные приложения стремятся предложить персонализированный опыт, адаптированный к вашим предпочтениям и стилю. Цветовые схемы, шрифты и элементы интерфейса могут изменяться в соответствии с вашими предпочтениями, создавая уникальный и индивидуальный вид для каждого пользователя.\n" +
                "\n" +
                "Функциональность и разнообразие: От социальных сетей и мессенджеров до приложений для фотографий, медиа-контента, игр и продуктивности - в мире \"Material You\" вы найдете широкий спектр приложений, позволяющих вам делать все, что вам нужно, прямо с вашего мобильного устройства.\n" +
                "\n" +
                "Безопасность и конфиденциальность: Важно, чтобы мобильные приложения обеспечивали высокий уровень безопасности и защиты конфиденциальности данных. Платформы \"Material You\" предоставляют средства для обеспечения безопасности пользователей и их данных, чтобы вы могли чувствовать себя уверенно при использовании вашего мобильного устройства.");
        return root;
    }
}