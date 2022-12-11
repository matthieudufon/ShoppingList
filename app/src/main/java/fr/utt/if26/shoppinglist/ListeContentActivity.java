package fr.utt.if26.shoppinglist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.utt.if26.shoppinglist.adapters.ContentListAdapter;
import fr.utt.if26.shoppinglist.entities.AlimentEntity;
import fr.utt.if26.shoppinglist.entities.ComposeEntity;
import fr.utt.if26.shoppinglist.entities.ListeEntity;
import fr.utt.if26.shoppinglist.viewModels.AlimentViewModel;
import fr.utt.if26.shoppinglist.viewModels.ComposeViewModel;
import fr.utt.if26.shoppinglist.viewModels.ListeViewModel;

public class ListeContentActivity extends AppCompatActivity {

    private TextView textViewNom;
    private TextView textViewLieu;
    private TextView textViewDate;
    private AutoCompleteTextView autoCompleteTextViewAliment;
    private ImageButton imageButton;
    private static List<AlimentEntity> alimentList;
    private AlimentEntity selectedAliment;

    private ListeViewModel listeViewModel;
    private AlimentViewModel alimentViewModel;
    private ComposeViewModel composeViewModel;

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
        this.alimentList = new ArrayList<AlimentEntity>();

        listeViewModel = new ViewModelProvider(this).get(ListeViewModel.class);
        alimentViewModel = new ViewModelProvider(this).get(AlimentViewModel.class);
        composeViewModel = new ViewModelProvider(this).get(ComposeViewModel.class);


        RecyclerView recyclerView = findViewById(R.id.liste_content_rv);
        final ContentListAdapter adapter = new ContentListAdapter(new ContentListAdapter.ListeDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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


        ArrayAdapter<AlimentEntity> adapterAutoComplete = new ArrayAdapter<AlimentEntity>(this, android.R.layout.simple_list_item_1, alimentList);
        autoCompleteTextViewAliment.setAdapter(adapterAutoComplete);
        alimentViewModel.getAllAliments().observe(this, adapterAutoComplete::addAll);
        final Observer<List<AlimentEntity>> observerAutoComplete = new Observer<List<AlimentEntity>>() {
            @Override
            public void onChanged(List<AlimentEntity> aliments) {
                alimentList = aliments;
            }
        };
        alimentViewModel.getAllAliments().observe(this, observerAutoComplete);

        autoCompleteTextViewAliment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Object item = adapterView.getItemAtPosition(position);
                if (item instanceof AlimentEntity) {
                    selectedAliment = (AlimentEntity) item;
                }
            }
        });

        imageButton.setOnClickListener(view -> {
            try {
                composeViewModel.insert(new ComposeEntity(selectedAliment.getId(), id, 1, 1));
                final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            } catch (Exception e) {
                Toast.makeText(
                        getApplicationContext(),
                        "Entrez un aliment valide",
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}