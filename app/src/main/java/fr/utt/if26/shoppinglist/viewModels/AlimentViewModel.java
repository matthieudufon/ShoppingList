package fr.utt.if26.shoppinglist.viewModels;

import android.app.Application;
import android.text.Editable;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.LinkedHashMap;
import java.util.List;

import fr.utt.if26.shoppinglist.AppRepository;
import fr.utt.if26.shoppinglist.entities.AlimentEntity;

public class AlimentViewModel extends AndroidViewModel {

    private AppRepository repository;

    private final LiveData<List<AlimentEntity>> alimentList;

    public AlimentViewModel (Application application) {
        super(application);
        repository = new AppRepository(application);
        alimentList = repository.getAllAliments();
    }

    public LiveData<List<AlimentEntity>> getAllAliments() {
        return this.alimentList;
    }

    public LiveData<AlimentEntity> getAlimentById(int id) {
        return repository.getAlimentById(id);
    }

    public LiveData<List<AlimentEntity>> getAlimentByList(int listeId) {
        return repository.getAlimentByListe(listeId);
    }

    public LiveData<List<String>> getAllAlimentName() {
        return repository.getAllAlimentName();
    }

    public void insert(AlimentEntity aliment) {
        repository.insert(aliment);
    }

    public LiveData<AlimentEntity> getAlimentByNom(String nom) {
        return repository.getAlimentByNom(nom);
    }

}
