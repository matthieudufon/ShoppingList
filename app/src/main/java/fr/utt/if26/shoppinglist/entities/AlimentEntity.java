package fr.utt.if26.shoppinglist.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName= "aliment_table")
public class AlimentEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "aliment_id")
    private int id;

    @NonNull
    @ColumnInfo(name = "aliment_nom")
    private String nom;

    @NonNull
    @ColumnInfo(name = "aliment_categorie")
    private String categorie;

    public AlimentEntity (@NonNull String nom, @NonNull String categorie) {
        this.nom = nom;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCategorie() {
        return categorie;
    }
}
