package fr.utt.if26.shoppinglist.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

public class AlimentAndCompose {

    @Embedded public AlimentEntity aliment;
    @Relation(
            parentColumn = "aliment_id",
            entityColumn = "aliment_id"
    )
    public ComposeEntity compose;

}
