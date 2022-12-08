package fr.utt.if26.shoppinglist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import fr.utt.if26.shoppinglist.adapters.ContentListAdapter;
import fr.utt.if26.shoppinglist.entities.ListeEntity;
import fr.utt.if26.shoppinglist.viewModels.AlimentViewModel;
import fr.utt.if26.shoppinglist.viewModels.ComposeViewModel;
import fr.utt.if26.shoppinglist.viewModels.ListeViewModel;

public class ListeContentActivity extends AppCompatActivity {

    private TextView textViewId;
    private TextView textViewNom;
    private TextView textViewLieu;
    private TextView textViewDate;

    private ListeViewModel listeViewModel;
    private ComposeViewModel composeViewModel;
    private AlimentViewModel alimentViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_content);

        textViewId = findViewById(R.id.liste_content_tv1);
        textViewNom = findViewById(R.id.liste_content_tv2);
        textViewLieu = findViewById(R.id.liste_content_tv3);
        textViewDate = findViewById(R.id.liste_content_tv4);

        Integer id = getIntent().getExtras().getInt("id");

        RecyclerView recyclerView = findViewById(R.id.liste_content_rv);
        final ContentListAdapter adapter = new ContentListAdapter(new ContentListAdapter.ListeDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listeViewModel = new ViewModelProvider(this).get(ListeViewModel.class);
        composeViewModel = new ViewModelProvider(this).get(ComposeViewModel.class);
        alimentViewModel = new ViewModelProvider(this).get(AlimentViewModel.class);

        alimentViewModel.getAlimentByList(id).observe(this, adapter::submitList);

        final Observer<ListeEntity> observer = new Observer<ListeEntity>() {
            @Override
            public void onChanged(@Nullable final ListeEntity liste) {
                textViewId.setText(liste.getId().toString());
                textViewLieu.setText(liste.getLieu());
                textViewNom.setText(liste.getNom());
                textViewDate.setText(liste.getDate().toString());
            }
        };

        listeViewModel.getListeById(id).observe(this, observer);

    }
}