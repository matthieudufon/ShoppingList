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
    private Integer id;

    @NonNull
    @ColumnInfo(name = "liste_nom")
    private String nom;

    @ColumnInfo(name = "liste_lieu")
    private String lieu;

    public ListeEntity(@NonNull String nom, String lieu) {
        this.nom = nom;
        this.lieu = lieu;
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getLieu() { return lieu; }

    public void setId(Integer id) {
        this.id = id;
    }

}
