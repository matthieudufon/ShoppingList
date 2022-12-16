package fr.utt.if26.shoppinglist.entities.embedded;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import fr.utt.if26.shoppinglist.entities.AlimentEntity;
import fr.utt.if26.shoppinglist.entities.ComposeEntity;

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
