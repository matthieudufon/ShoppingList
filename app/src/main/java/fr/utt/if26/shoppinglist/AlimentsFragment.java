package fr.utt.if26.shoppinglist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.utt.if26.shoppinglist.adapters.AlimentListAdapter;
import fr.utt.if26.shoppinglist.viewModels.AlimentViewModel;


public class AlimentsFragment extends Fragment {

    RecyclerView recyclerView;

    private static AlimentViewModel alimentViewModel;

    public AlimentsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_aliments, container, false);

        recyclerView = result.findViewById(R.id.fragment_aliments_rv);
        final AlimentListAdapter adapter = new AlimentListAdapter(new AlimentListAdapter.AlimentDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(result.getContext()));

        alimentViewModel = new ViewModelProvider(this).get(AlimentViewModel.class);
        alimentViewModel.getAllAliments().observe(getViewLifecycleOwner(), adapter::submitList);

        return result;
    }

    public static AlimentViewModel getAlimentViewModel() {
        return alimentViewModel;
    }

}