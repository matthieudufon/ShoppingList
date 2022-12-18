package fr.utt.if26.shoppinglist;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

import fr.utt.if26.shoppinglist.adapters.ListeListAdapter;
import fr.utt.if26.shoppinglist.entities.ListeEntity;
import fr.utt.if26.shoppinglist.viewModels.ListeViewModel;


public class MainFragment extends Fragment {

    public static final int NEW_LISTE_ACTIVITY_REQUEST_CODE = 1;

    RecyclerView recyclerView;
    FloatingActionButton fab;

    private ListeViewModel listeViewModel;

    public MainFragment() {

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = result.findViewById(R.id.fragment_main_rv);
        final ListeListAdapter adapter = new ListeListAdapter(new ListeListAdapter.ListeDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(result.getContext()));

        listeViewModel = new ViewModelProvider(this).get(ListeViewModel.class);
        listeViewModel.getAllListes().observe(getViewLifecycleOwner(), adapter::submitList);

        fab = result.findViewById(R.id.fragment_main_fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(getActivity(), NewListeActivity.class);
            startActivityForResult(intent, NEW_LISTE_ACTIVITY_REQUEST_CODE);
        });

        return result;

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_LISTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ListeEntity liste = null;
            liste = new ListeEntity(
                    data.getStringExtra(NewListeActivity.EXTRA_REPLY_NOM),
                    data.getStringExtra(NewListeActivity.EXTRA_REPLY_LIEU),
                    new Date(data.getLongExtra(NewListeActivity.EXTRA_REPLY_DATE, new Date().getTime()))
            );
            listeViewModel.insert(liste);
        }
    }

}