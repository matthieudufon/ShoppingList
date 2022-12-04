package fr.utt.if26.shoppinglist.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.shoppinglist.AppRepository;
import fr.utt.if26.shoppinglist.entities.ComposeEntity;

public class ComposeViewModel extends AndroidViewModel {

    private AppRepository repository;

    private final LiveData<List<ComposeEntity>> composeList;

    public ComposeViewModel (Application application) {
        super(application);
        repository = new AppRepository(application);
        composeList = repository.getAllCompose();
    }

    LiveData<List<ComposeEntity>> getAllComposes() {
        return this.composeList;
    }

    public void insert(ComposeEntity compose) {
        repository.insert(compose);
    }

}
