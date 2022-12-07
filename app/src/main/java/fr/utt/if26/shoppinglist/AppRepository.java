package fr.utt.if26.shoppinglist;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.shoppinglist.entities.AlimentEntity;
import fr.utt.if26.shoppinglist.entities.ComposeEntity;
import fr.utt.if26.shoppinglist.entities.ListeEntity;

public class AppRepository {

    private AppDAO appDAO;
    private LiveData<List<AlimentEntity>> alimentList;
    private LiveData<List<ListeEntity>> listeList;
    private LiveData<List<ComposeEntity>> composeList;

    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        appDAO = db.appDAO();
        alimentList = appDAO.selectAllAliment();
        listeList = appDAO.selectAllListe();
        composeList = appDAO.selectAllCompose();
    }

    public AppDAO getAppDAO() {
        return appDAO;
    }

    public LiveData<List<AlimentEntity>> getAllAliments() {
        return alimentList;
    }

    public LiveData<List<ListeEntity>> getAllListe() {
        return listeList;
    }

    public LiveData<List<ComposeEntity>> getAllCompose() {
        return composeList;
    }

    public void insert(AlimentEntity aliment) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            appDAO.insert(aliment);
        });
    }

    public void insert(ListeEntity liste) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            appDAO.insert(liste);
        });
    }

    public void insert(ComposeEntity compose) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            appDAO.insert(compose);
        });
    }

}
