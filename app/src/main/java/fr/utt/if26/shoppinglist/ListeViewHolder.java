package fr.utt.if26.shoppinglist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListeViewHolder extends RecyclerView.ViewHolder {

    private final TextView listeItemView;
    private final TextView listeItemViewLieu;

    public ListeViewHolder(@NonNull View itemView) {
        super(itemView);
        listeItemView = itemView.findViewById(R.id.liste_item_tv1);
        listeItemViewLieu = itemView.findViewById(R.id.liste_item_tv2);
    }

    public void bind(String content, String lieu) {
        listeItemView.setText(content);
        listeItemViewLieu.setText(lieu);
    }

    static ListeViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.liste_item, parent, false);
        return new ListeViewHolder(view);
    }

}
