package fr.utt.if26.shoppinglist.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

import fr.utt.if26.shoppinglist.R;
import fr.utt.if26.shoppinglist.model.SharedPreferencesManager;
import fr.utt.if26.shoppinglist.view.adapters.ContentListAdapter;
import fr.utt.if26.shoppinglist.model.entities.embedded.AlimentAndCompose;
import fr.utt.if26.shoppinglist.model.entities.AlimentEntity;
import fr.utt.if26.shoppinglist.model.entities.ComposeEntity;
import fr.utt.if26.shoppinglist.model.entities.ListeEntity;
import fr.utt.if26.shoppinglist.viewModels.AlimentViewModel;
import fr.utt.if26.shoppinglist.viewModels.ComposeViewModel;
import fr.utt.if26.shoppinglist.viewModels.ListeViewModel;

public class ListeContentActivity extends AppCompatActivity {

    public static final int DEL_LISTE_ACTIVITY_REQUEST_CODE = 2;

    private Toolbar toolbar;
    private TextView textViewNom;
    private TextView textViewLieu;
    private TextView textViewDate;
    private AutoCompleteTextView autoCompleteTextViewAliment;
    private ImageButton imageButtonAdd;
    private ImageButton imageButtonEdit;
    private static List<AlimentEntity> alimentList;
    private AlimentEntity selectedAliment;
    public static List<ComposeEntity> updatedComposeEntities;
    private Integer id;
    private static final String EXTRA_ID = "ID";
    public static final String DELETE_LISTE = "DELETE";

    private ListeViewModel listeViewModel;
    private AlimentViewModel alimentViewModel;
    private static ComposeViewModel composeViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(new SharedPreferencesManager(this).retrieveInt("theme", R.style.Theme_ShoppingList));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_content);

        textViewNom = findViewById(R.id.liste_content_tv_nom);
        textViewLieu = findViewById(R.id.liste_content_tv_lieu);
        textViewDate = findViewById(R.id.liste_content_tv_date);
        autoCompleteTextViewAliment = (AutoCompleteTextView) findViewById(R.id.liste_content_ac_aliment);
        imageButtonAdd = (ImageButton) findViewById(R.id.liste_content_bt_add);
        imageButtonEdit = (ImageButton) findViewById(R.id.liste_content_bt_edit);
        toolbar = findViewById(R.id.liste_content_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        switch (new SharedPreferencesManager(this).retrieveInt("theme", R.style.Theme_ShoppingList)) {
            case R.style.Theme_ShoppingListBanana:
                toolbar.setBackgroundColor(getResources().getColor(R.color.banana_color_strong));
                break;
            case R.style.Theme_ShoppingListWatermelon:
                toolbar.setBackgroundColor(getResources().getColor(R.color.watermelon_color_strong));
                break;
            default:
                toolbar.setBackgroundColor(getResources().getColor(R.color.apple_color_strong));
                break;
        }

        id = getIntent().getExtras().getInt("id");
        this.alimentList = new ArrayList<AlimentEntity>();
        updatedComposeEntities = new ArrayList<ComposeEntity>();

        listeViewModel = new ViewModelProvider(this).get(ListeViewModel.class);
        alimentViewModel = new ViewModelProvider(this).get(AlimentViewModel.class);
        composeViewModel = new ViewModelProvider(this).get(ComposeViewModel.class);


        RecyclerView recyclerView = findViewById(R.id.liste_content_rv);
        final ContentListAdapter adapter = new ContentListAdapter(new ContentListAdapter.ListeDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        alimentViewModel.getAlimentAndComposeByListe(id).observe(this, options -> {
            for (AlimentAndCompose alimentAndCompose : options) {
                if (alimentAndCompose.composes.size() > 1) {
                    alimentAndCompose.composes.removeIf(compose -> compose.getListe_id() != id);
                }
            }
            adapter.submitList(options);
        });


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


        autoCompleteTextViewAliment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Object item = adapterView.getItemAtPosition(position);
                if (item instanceof AlimentEntity) {
                    selectedAliment = (AlimentEntity) item;
                }
            }
        });


        imageButtonAdd.setOnClickListener(view -> {
            try {
                composeViewModel.insert(new ComposeEntity(selectedAliment.getId(), id, 1, 1, false));
                final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                for (ComposeEntity compose : updatedComposeEntities) {
                    composeViewModel.updateCompose(compose);
                }
            } catch (Exception e) {
                Toast.makeText(
                        getApplicationContext(),
                        "Entrez un aliment valide",
                        Toast.LENGTH_LONG).show();
            }
        });

        imageButtonEdit.setOnClickListener(view -> {
            Intent intent = new Intent(ListeContentActivity.this, EditListeActivity.class);
            intent.putExtra(EXTRA_ID, id);
            startActivityForResult(intent, DEL_LISTE_ACTIVITY_REQUEST_CODE);
        });

    }

    public static ComposeViewModel getComposeViewModel() {
        return composeViewModel;
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (ComposeEntity compose : updatedComposeEntities) {
            composeViewModel.updateCompose(compose);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DEL_LISTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Intent intent = new Intent();
            intent.putExtra(DELETE_LISTE, data.getIntExtra(EditListeActivity.DELETE_LISTE, 0));
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}