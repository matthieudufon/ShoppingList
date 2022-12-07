package fr.utt.if26.shoppinglist.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.shoppinglist.AppRepository;
import fr.utt.if26.shoppinglist.entities.ListeEntity;

public class ListeViewModel extends AndroidViewModel {

    private AppRepository repository;

    private final LiveData<List<ListeEntity>> listeList;

    public ListeViewModel (Application application) {
        super(application);
        repository = new AppRepository(application);
        listeList = repository.getAllListe();
    }

    public LiveData<List<ListeEntity>> getAllListes() {
        return this.listeList;
    }

    public LiveData<ListeEntity> getListeById(int id) {
        return repository.getListeById(id);
    }

    public void insert(ListeEntity liste) {
        repository.insert(liste);
    }
}
