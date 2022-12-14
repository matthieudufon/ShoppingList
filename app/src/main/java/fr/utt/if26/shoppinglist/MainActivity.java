package fr.utt.if26.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

import fr.utt.if26.shoppinglist.adapters.ListeListAdapter;
import fr.utt.if26.shoppinglist.entities.ListeEntity;
import fr.utt.if26.shoppinglist.viewModels.ListeViewModel;

public class MainActivity extends AppCompatActivity {

    private ListeViewModel listeViewModel;
    public static final int NEW_LISTE_ACTIVITY_REQUEST_CODE = 1;
    public static final int DEL_LISTE_ACTIVITY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.activity_main_rv);
        final ListeListAdapter adapter = new ListeListAdapter(new ListeListAdapter.ListeDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listeViewModel = new ViewModelProvider(this).get(ListeViewModel.class);
        listeViewModel.getAllListes().observe(this, adapter::submitList);

        FloatingActionButton fab = findViewById(R.id.activity_main_fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewListeActivity.class);
            startActivityForResult(intent, NEW_LISTE_ACTIVITY_REQUEST_CODE);
        });

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
        } else if (requestCode == DEL_LISTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Log.d("DEBUG-MATTHIEU", "test");
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Pas enregistré",
                    Toast.LENGTH_LONG).show();
        }
    }

}