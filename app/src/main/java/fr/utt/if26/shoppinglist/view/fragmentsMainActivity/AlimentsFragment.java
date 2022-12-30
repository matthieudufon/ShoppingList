package fr.utt.if26.shoppinglist.view.fragmentsMainActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import fr.utt.if26.shoppinglist.R;
import fr.utt.if26.shoppinglist.model.SharedPreferencesManager;
import fr.utt.if26.shoppinglist.view.adapters.AlimentListAdapter;
import fr.utt.if26.shoppinglist.model.entities.AlimentEntity;
import fr.utt.if26.shoppinglist.viewModels.AlimentViewModel;


public class AlimentsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerView;
    private EditText editTextNom;
    private Spinner spinnerCategorie;
    private ImageButton validateButton;

    private static AlimentViewModel alimentViewModel;

    private List<String> spinnerOptions = new ArrayList<String>();
    private String spinnerChoice = "Céréales";

    public AlimentsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_aliments, container, false);

        editTextNom = result.findViewById(R.id.fragment_aliments_et);
        spinnerCategorie = result.findViewById(R.id.fragment_aliments_spinner);
        validateButton = result.findViewById(R.id.fragment_aliments_ib);

        switch(new SharedPreferencesManager(getActivity()).retrieveInt("theme", R.style.Theme_ShoppingList)) {
            case R.style.Theme_ShoppingListBanana:
                validateButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.banana_color_strong)));
                break;
            case R.style.Theme_ShoppingListWatermelon:
                validateButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.watermelon_color_strong)));
                break;
            default:
                validateButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.apple_color_strong)));
                break;
        }

        recyclerView = result.findViewById(R.id.fragment_aliments_rv);
        final AlimentListAdapter adapter = new AlimentListAdapter(new AlimentListAdapter.AlimentDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(result.getContext()));

        alimentViewModel = new ViewModelProvider(this).get(AlimentViewModel.class);
        alimentViewModel.getAllAliments().observe(getViewLifecycleOwner(), adapter::submitList);

        spinnerOptions.add("Céréales");
        spinnerOptions.add("Boissons");
        spinnerOptions.add("Viande et poisson");
        spinnerOptions.add("Légumes et fruits");
        spinnerOptions.add("Produits sucrés");
        spinnerOptions.add("Produits laitiers");
        spinnerOptions.add("Plats composés");
        spinnerOptions.add("Autre");
        spinnerCategorie.setOnItemSelectedListener(this);

        ArrayAdapter spinnerAdapter = new ArrayAdapter(result.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinnerOptions);
        spinnerAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinnerCategorie.setAdapter(spinnerAdapter);

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editTextNom.getText().toString().equals("")) {
                    AlimentEntity aliment = new AlimentEntity(editTextNom.getText().toString(), spinnerChoice);
                    alimentViewModel.insert(aliment);
                    editTextNom.setText("");
                }
            }
        });

        return result;
    }

    public static AlimentViewModel getAlimentViewModel() {
        return alimentViewModel;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnerChoice = spinnerOptions.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}