package fr.utt.if26.shoppinglist;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
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
        switch(new SharedPreferencesManager(getActivity()).retrieveInt("theme", R.style.Theme_ShoppingList)) {
            case R.style.Theme_ShoppingListBanana:
                buttonWatermelon.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.banana_color_strong)));
                buttonApple.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.banana_color_strong)));
                buttonBanana.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.banana_color_strong)));
                break;
            case R.style.Theme_ShoppingListWatermelon:
                buttonWatermelon.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.watermelon_color_strong)));
                buttonApple.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.watermelon_color_strong)));
                buttonBanana.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.watermelon_color_strong)));
                break;
            default:
                buttonWatermelon.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.apple_color_strong)));
                buttonApple.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.apple_color_strong)));
                buttonBanana.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.apple_color_strong)));
                break;
        }
        buttonWatermelon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SharedPreferencesManager(result.getContext()).storeInt("theme", R.style.Theme_ShoppingListWatermelon);
                reload();
            }
        });
        buttonApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SharedPreferencesManager(result.getContext()).storeInt("theme", R.style.Theme_ShoppingList);
                reload();
            }
        });
        buttonBanana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SharedPreferencesManager(result.getContext()).storeInt("theme", R.style.Theme_ShoppingListBanana);
                reload();
            }
        });

        return result;
    }

    public void reload() {
        getActivity().finish();
        getActivity().overridePendingTransition(0, 0);
        getActivity().startActivity(getActivity().getIntent());
        getActivity().overridePendingTransition(0, 0);
    }

}