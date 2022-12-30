package fr.utt.if26.shoppinglist.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName= "aliment_table", indices = {@Index(value = {"aliment_nom"}, unique = true)})
public class AlimentEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "aliment_id")
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String toString() {
        return nom;
    }

}
