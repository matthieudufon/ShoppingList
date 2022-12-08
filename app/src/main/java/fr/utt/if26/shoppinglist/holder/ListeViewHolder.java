package fr.utt.if26.shoppinglist.holder;

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
import java.util.Date;

import fr.utt.if26.shoppinglist.ListeContentActivity;
import fr.utt.if26.shoppinglist.R;

public class ListeViewHolder extends RecyclerView.ViewHolder {

    private final TextView listeItemView;
    private final TextView listeItemViewLieu;
    private final TextView listeItemViewDate;
    private final ImageButton listeItemImageButton;
    private int id = 1;

    public ListeViewHolder(@NonNull View itemView) {
        super(itemView);
        listeItemView = itemView.findViewById(R.id.liste_item_tv1);
        listeItemViewLieu = itemView.findViewById(R.id.liste_item_tv2);
        listeItemViewDate = itemView.findViewById(R.id.liste_item_tv3);
        listeItemImageButton = itemView.findViewById(R.id.liste_item_bt);
        listeItemImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListeContentActivity.class);
                intent.putExtra("id", id);
                view.getContext().startActivity(intent);
            }
        });
    }

    public void bind(String content, String lieu, Date date, Integer id) {
        listeItemView.setText(content);
        listeItemViewLieu.setText(lieu);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        listeItemViewDate.setText(strDate);
        this.id = id;
    }

    public static ListeViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.liste_item, parent, false);
        return new ListeViewHolder(view);
    }

}
