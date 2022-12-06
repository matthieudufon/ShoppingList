package fr.utt.if26.shoppinglist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListeViewHolder extends RecyclerView.ViewHolder {

    private final TextView listeItemView;
    private final TextView listeItemViewLieu;
    private final TextView listeItemViewDate;

    public ListeViewHolder(@NonNull View itemView) {
        super(itemView);
        listeItemView = itemView.findViewById(R.id.liste_item_tv1);
        listeItemViewLieu = itemView.findViewById(R.id.liste_item_tv2);
        listeItemViewDate = itemView.findViewById(R.id.liste_item_tv3);
    }

    public void bind(String content, String lieu, Date date) {
        listeItemView.setText(content);
        listeItemViewLieu.setText(lieu);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        listeItemViewDate.setText(strDate);
    }

    static ListeViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.liste_item, parent, false);
        return new ListeViewHolder(view);
    }

}
