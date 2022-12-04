package fr.utt.if26.shoppinglist.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName= "liste_table")
public class ListeEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "liste_id")
    private int id;

    @NonNull
    @ColumnInfo(name = "liste_nom")
    private String nom;

    public ListeEntity(@NonNull String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
