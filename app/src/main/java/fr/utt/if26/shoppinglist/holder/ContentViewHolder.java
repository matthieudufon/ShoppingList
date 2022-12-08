package fr.utt.if26.shoppinglist.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import fr.utt.if26.shoppinglist.R;
import fr.utt.if26.shoppinglist.entities.AlimentEntity;

public class ContentViewHolder extends RecyclerView.ViewHolder {

    private final TextView alimentNom;
    private final TextView alimentCategorie;

    private ContentViewHolder(View itemView) {
        super(itemView);
        alimentNom = itemView.findViewById(R.id.content_item_tv1);
        alimentCategorie = itemView.findViewById(R.id.content_item_tv2);
    }

    public void bind(AlimentEntity aliment) {
        this.alimentNom.setText(aliment.getNom());
        this.alimentCategorie.setText(aliment.getCategorie());
    }

    public static ContentViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_item, parent, false);
        return new ContentViewHolder(view);
    }

}
