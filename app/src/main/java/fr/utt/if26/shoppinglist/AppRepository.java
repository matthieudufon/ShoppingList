package fr.utt.if26.shoppinglist;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.HashMap;
import java.util.List;

import fr.utt.if26.shoppinglist.entities.AlimentAndCompose;
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

    public LiveData<AlimentEntity> getAlimentById(int id) {
        return appDAO.selectAlimentById(id);
    }

    public LiveData<List<ListeEntity>> getAllListe() {
        return listeList;
    }

    public LiveData<ListeEntity> getListeById(int id) {
        return appDAO.selectListeById(id);
    }

    public LiveData<List<ComposeEntity>> getAllCompose() {
        return composeList;
    }

    public LiveData<ComposeEntity> getComposeById(int alimentId, int listeId) {
        return appDAO.selectComposeById(alimentId, listeId);
    }

    public LiveData<List<AlimentEntity>> getAlimentByListe(int listeId) {
        return appDAO.selectAlimentByListe(listeId);
    }

    public LiveData<List<AlimentAndCompose>> getAlimentAndComposeByListe(int listeId) {
        return appDAO.selectAlimentAndComposeByListe(listeId);
    }

    public void updateCompose(ComposeEntity compose) {
        appDAO.updateCompose(compose);
    }

    public void updateListe(ListeEntity liste) {
        appDAO.updateListe(liste);
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

    public void deleteListeById(int id) {
        appDAO.deleteListeById(id);
    }

}
