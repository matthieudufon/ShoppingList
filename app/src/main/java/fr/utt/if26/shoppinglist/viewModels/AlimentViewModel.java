package fr.utt.if26.shoppinglist.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.shoppinglist.AppRepository;
import fr.utt.if26.shoppinglist.entities.embedded.AlimentAndCompose;
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

    public LiveData<List<AlimentAndCompose>> getAlimentAndCompose() {
        return repository.getAlimentAndCompose();
    }

    public LiveData<List<AlimentAndCompose>> getAlimentAndComposeByListe(int listeId) {
        return repository.getAlimentAndComposeByListe(listeId);
    }

    public void insert(AlimentEntity aliment) {
        repository.insert(aliment);
    }

    public void deleteAlimentById(int id) {
        repository.deleteAlimentById(id);
    }

}
