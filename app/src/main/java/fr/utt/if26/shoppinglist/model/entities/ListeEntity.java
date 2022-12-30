package fr.utt.if26.shoppinglist.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName= "liste_table")
public class ListeEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "liste_id")
    private Integer id;

    @NonNull
    @ColumnInfo(name = "liste_nom")
    private String nom;

    @NonNull
    @ColumnInfo(name = "liste_lieu")
    private String lieu;

    @NonNull
    @ColumnInfo(name = "liste_date")
    private Date date;

    public ListeEntity(@NonNull String nom, String lieu, Date date) {
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getLieu() { return lieu; }

    public Date getDate() { return this.date; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
