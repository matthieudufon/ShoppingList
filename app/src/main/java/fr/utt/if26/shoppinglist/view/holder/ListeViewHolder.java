package fr.utt.if26.shoppinglist.view.holder;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import fr.utt.if26.shoppinglist.view.ListeContentActivity;
import fr.utt.if26.shoppinglist.R;
import fr.utt.if26.shoppinglist.model.entities.ListeEntity;

public class ListeViewHolder extends RecyclerView.ViewHolder {

    private final TextView listeItemViewNom;
    private final TextView listeItemViewLieu;
    private final TextView listeItemViewDate;
    private final ImageButton listeItemImageButton;
    private int id = 1;

    public static final int CONTENT_LIST_ACTIVITY = 3;

    public ListeViewHolder(@NonNull View itemView) {
        super(itemView);
        listeItemViewNom = itemView.findViewById(R.id.liste_item_tv1);
        listeItemViewLieu = itemView.findViewById(R.id.liste_item_tv2);
        listeItemViewDate = itemView.findViewById(R.id.liste_item_tv3);
        listeItemImageButton = itemView.findViewById(R.id.liste_item_bt);
        listeItemImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListeContentActivity.class);
                intent.putExtra("id", id);
                ((Activity)view.getContext()).startActivityForResult(intent, CONTENT_LIST_ACTIVITY);
            }
        });
    }

    public void bind(ListeEntity liste) {
        listeItemViewNom.setText(liste.getNom());
        listeItemViewLieu.setText(liste.getLieu());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(liste.getDate());
        listeItemViewDate.setText(strDate);
        this.id = liste.getId();
    }

    public static ListeViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.liste_item, parent, false);
        return new ListeViewHolder(view);
    }

}
