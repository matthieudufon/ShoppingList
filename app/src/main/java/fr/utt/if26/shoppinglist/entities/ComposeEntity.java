package fr.utt.if26.shoppinglist.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "compose_table", primaryKeys = {"aliment_id", "liste_id"})
public class ComposeEntity {

    @NonNull
    @ColumnInfo(name = "compose_aliment_id")
    private int aliment_id;

    @NonNull
    @ColumnInfo(name = "compose_liste_id")
    private int liste_id;

    @ColumnInfo(name = "compose_quantite")
    private int quantite;

    public ComposeEntity(@NonNull int aliment_id,@NonNull  int liste_id,@NonNull  int quantite) {
        this.aliment_id = aliment_id;
        this.liste_id = liste_id;
        this.quantite = quantite;
    }

    public int getAliment_id() {
        return aliment_id;
    }

    public int getListe_id() {
        return liste_id;
    }

    public int getQuantite() {
        return quantite;
    }
}
