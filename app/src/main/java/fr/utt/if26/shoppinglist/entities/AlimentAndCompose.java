package fr.utt.if26.shoppinglist.entities;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class AlimentAndCompose {

    @Embedded public AlimentEntity aliment;
    @Relation(
            parentColumn = "aliment_id",
            entityColumn = "aliment_id"
    )
    public List<ComposeEntity> composes;

    /*@Embedded public ListeEntity listeEntity;
    @Relation(
            parentColumn = "liste_id",
            entityColumn = "aliment_id",
            associateBy = @Junction(ComposeEntity.class)
    )
    public List<AlimentEntity> alimentEntityList;*/

    public String toString() {
        return composes.toString();
    }

}
