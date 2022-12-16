package fr.utt.if26.shoppinglist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.utt.if26.shoppinglist.entities.embedded.AlimentAndCompose;
import fr.utt.if26.shoppinglist.entities.AlimentEntity;
import fr.utt.if26.shoppinglist.entities.ComposeEntity;
import fr.utt.if26.shoppinglist.entities.ListeEntity;

@Dao
public interface AppDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AlimentEntity aliment);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ListeEntity liste);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ComposeEntity compose);

    @Query("DELETE FROM aliment_table")
    void deleteAllAliment();

    @Query("DELETE FROM liste_table")
    void deleteAllListe();

    @Query("DELETE FROM compose_table")
    void deleteAllCompose();

    @Query("DELETE FROM aliment_table WHERE aliment_id = :alimentId")
    void deleteAlimentById(int alimentId);

    @Query("DELETE FROM liste_table WHERE liste_id = :listeId")
    void deleteListeById(int listeId);

    @Query("DELETE FROM compose_table WHERE aliment_id = :alimentId AND liste_id = :listeId")
    void deleteListeById(int alimentId, int listeId);

    @Query("SELECT * FROM aliment_table")
    LiveData<List<AlimentEntity>> selectAllAliment();

    @Query("SELECT * FROM liste_table")
    LiveData<List<ListeEntity>> selectAllListe();

    @Query("SELECT * FROM compose_table")
    LiveData<List<ComposeEntity>> selectAllCompose();

    @Query("SELECT * FROM aliment_table WHERE aliment_id = :alimentId")
    LiveData<AlimentEntity> selectAlimentById(int alimentId);

    @Query("SELECT * FROM liste_table WHERE liste_id = :listeId")
    LiveData<ListeEntity> selectListeById(int listeId);

    @Query("SELECT * FROM compose_table WHERE aliment_id = :alimentId AND liste_id = :listeId")
    LiveData<ComposeEntity> selectComposeById(int alimentId, int listeId);

    @Query("SELECT * FROM aliment_table INNER JOIN compose_table ON compose_table.aliment_id = aliment_table.aliment_id WHERE liste_id = :listeId")
    LiveData<List<AlimentEntity>> selectAlimentByListe(int listeId);

    @Query("SELECT * FROM aliment_table INNER JOIN compose_table ON compose_table.aliment_id = aliment_table.aliment_id WHERE compose_table.liste_id = :listeId ORDER BY compose_table.compose_coche, compose_table.compose_priorite, aliment_table.aliment_categorie")
    LiveData<List<AlimentAndCompose>> selectAlimentAndComposeByListe(int listeId);

    @Update
    void updateAliment(AlimentEntity aliment);

    @Update
    void updateListe(ListeEntity liste);

    @Update
    void updateCompose(ComposeEntity compose);

}
