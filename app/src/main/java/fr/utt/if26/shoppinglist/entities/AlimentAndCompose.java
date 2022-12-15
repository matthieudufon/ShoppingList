package fr.utt.if26.shoppinglist.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class AlimentAndCompose {

    @Embedded public AlimentEntity aliment;
    @Relation(
            parentColumn = "aliment_id",
            entityColumn = "aliment_id"
    )
    public List<ComposeEntity> composes;

    public String toString() {
        return composes.toString();
    }

}
