package fr.utt.if26.shoppinglist.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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

    LiveData<List<AlimentEntity>> getAllAliments() {
        return this.alimentList;
    }

    public void insert(AlimentEntity aliment) {
        repository.insert(aliment);
    }

}
