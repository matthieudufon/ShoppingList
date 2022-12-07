package fr.utt.if26.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import fr.utt.if26.shoppinglist.entities.ListeEntity;
import fr.utt.if26.shoppinglist.viewModels.ListeViewModel;

public class ListeContentActivity extends AppCompatActivity {

    private TextView textViewId;
    private TextView textViewNom;
    private TextView textViewLieu;
    private TextView textViewDate;

    private ListeViewModel listeViewModel;

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

        listeViewModel = new ViewModelProvider(this).get(ListeViewModel.class);

        final Observer<List<ListeEntity>> listeObserver = new Observer<List<ListeEntity>>() {
            @Override
            public void onChanged(List<ListeEntity> listListeEntity) {
                ListeEntity listeEntityResult = null;
                for (ListeEntity listeEntity : listListeEntity) {
                    if (listeEntity.getId() == id) {
                        listeEntityResult = listeEntity;
                    }
                }
                textViewId.setText(listeEntityResult.getId().toString());
                textViewLieu.setText(listeEntityResult.getLieu());
                textViewNom.setText(listeEntityResult.getNom());
                textViewDate.setText(listeEntityResult.getDate().toString());
            }
        };

        listeViewModel.getAllListes().observe(this, listeObserver);

        /*AppRepository repository = new AppRepository(getApplication());
        // repository != null mais liste == null
        LiveData<ListeEntity> test = repository.getAppDAO().selectListeById(id).;
        if (test == null) {
            Log.d("DEBUG-MATTHIEU", "test == null");
        }
        ListeEntity liste = repository.getAppDAO().selectListeById(id).getValue();

        textViewId.setText(liste.getId().toString());
        textViewLieu.setText(liste.getLieu());
        textViewNom.setText(liste.getNom());
        textViewDate.setText(liste.getDate().toString());*/
    }
}