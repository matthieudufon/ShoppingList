package fr.utt.if26.shoppinglist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.utt.if26.shoppinglist.adapters.ContentListAdapter;
import fr.utt.if26.shoppinglist.entities.ListeEntity;
import fr.utt.if26.shoppinglist.viewModels.AlimentViewModel;
import fr.utt.if26.shoppinglist.viewModels.ListeViewModel;

public class ListeContentActivity extends AppCompatActivity {

    private TextView textViewNom;
    private TextView textViewLieu;
    private TextView textViewDate;
    private AutoCompleteTextView autoCompleteTextViewAliment;
    private ImageButton imageButton;
    private static List<String> aliments = new ArrayList<String>();

    private ListeViewModel listeViewModel;
    private AlimentViewModel alimentViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_content);

        textViewNom = findViewById(R.id.liste_content_tv_nom);
        textViewLieu = findViewById(R.id.liste_content_tv_lieu);
        textViewDate = findViewById(R.id.liste_content_tv_date);
        autoCompleteTextViewAliment = (AutoCompleteTextView) findViewById(R.id.liste_content_ac_aliment);
        imageButton = (ImageButton) findViewById(R.id.liste_content_bt);

        Integer id = getIntent().getExtras().getInt("id");

        RecyclerView recyclerView = findViewById(R.id.liste_content_rv);
        final ContentListAdapter adapter = new ContentListAdapter(new ContentListAdapter.ListeDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayAdapter<String> adapterAutoComplete = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, aliments);
        autoCompleteTextViewAliment.setAdapter(adapterAutoComplete);

        listeViewModel = new ViewModelProvider(this).get(ListeViewModel.class);
        alimentViewModel = new ViewModelProvider(this).get(AlimentViewModel.class);

        alimentViewModel.getAlimentByList(id).observe(this, adapter::submitList);

        final Observer<ListeEntity> observer = new Observer<ListeEntity>() {
            @Override
            public void onChanged(@Nullable final ListeEntity liste) {
                textViewLieu.setText(liste.getLieu());
                textViewNom.setText(liste.getNom());
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String strDate = dateFormat.format(liste.getDate());
                textViewDate.setText(strDate);
            }
        };

        listeViewModel.getListeById(id).observe(this, observer);

        alimentViewModel.getAllAlimentName().observe(this, adapterAutoComplete::addAll);

        final Observer<List<String>> observerAutoComplete = new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                aliments = strings;
            }
        };

        alimentViewModel.getAllAlimentName().observe(this, observerAutoComplete);

    }
}