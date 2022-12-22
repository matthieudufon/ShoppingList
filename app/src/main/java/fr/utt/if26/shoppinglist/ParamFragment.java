package fr.utt.if26.shoppinglist;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ParamFragment extends Fragment {

    private Button buttonWatermelon;
    private Button buttonApple;
    private Button buttonBanana;

    public ParamFragment() {

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_param, container, false);

        buttonWatermelon = result.findViewById(R.id.fragment_param_bt1);
        buttonApple = result.findViewById(R.id.fragment_param_bt2);
        buttonBanana = result.findViewById(R.id.fragment_param_bt3);
        /*buttonWatermelon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getTheme().applyStyle(R.style.Theme_ShoppingListWatermelon, true);
                System.out.println(getActivity().getTheme().toString());
            }
        });*/

        return result;
    }
}