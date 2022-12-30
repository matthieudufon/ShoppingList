package fr.utt.if26.shoppinglist.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "compose_table",
        primaryKeys = {"aliment_id", "liste_id"},
        foreignKeys = {
        @ForeignKey(entity = AlimentEntity.class,
                parentColumns = "aliment_id",
                childColumns = "aliment_id",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = ListeEntity.class,
                parentColumns = "liste_id",
                childColumns = "liste_id",
                onDelete = ForeignKey.CASCADE)})
public class ComposeEntity {

    @NonNull
    @ColumnInfo(name = "aliment_id")
    private Integer aliment_id;

    @NonNull
    @ColumnInfo(name = "liste_id")
    private Integer liste_id;

    @ColumnInfo(name = "compose_quantite")
    private int quantite;

    @ColumnInfo(name = "compose_priorite")
    private int priorite;

    @ColumnInfo(name = "compose_coche")
    private boolean coche;

    public ComposeEntity(@NonNull int aliment_id, @NonNull int liste_id, @NonNull int quantite, int priorite, boolean coche) {
        this.aliment_id = aliment_id;
        this.liste_id = liste_id;
        this.quantite = quantite;
        this.priorite = priorite;
        this.coche = coche;
    }

    public Integer getAliment_id() {
        return aliment_id;
    }

    public Integer getListe_id() {
        return liste_id;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getPriorite() { return priorite; }

    public boolean getCoche() {
        return coche;
    }

    public String toString() {
        return this.liste_id.toString();
    }

}
