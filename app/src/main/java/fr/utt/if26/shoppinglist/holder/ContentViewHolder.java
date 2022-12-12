package fr.utt.if26.shoppinglist.holder;

import android.app.Application;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import fr.utt.if26.shoppinglist.ListeContentActivity;
import fr.utt.if26.shoppinglist.R;
import fr.utt.if26.shoppinglist.entities.AlimentEntity;
import fr.utt.if26.shoppinglist.entities.ComposeEntity;
import fr.utt.if26.shoppinglist.viewModels.ComposeViewModel;

public class ContentViewHolder extends RecyclerView.ViewHolder {

    private final TextView alimentNom;
    private final ImageView alimentCategorieImage;
    private final CheckBox alimentCheckbox;

    private ComposeEntity compose;

    private ContentViewHolder(View itemView) {
        super(itemView);
        alimentNom = itemView.findViewById(R.id.content_item_tv1);
        alimentCategorieImage = (ImageView) itemView.findViewById(R.id.content_item_iv);
        alimentCheckbox = (CheckBox) itemView.findViewById(R.id.content_item_cb);
        alimentCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Erreur à corriger
                ComposeViewModel composeViewModel = ListeContentActivity.composeViewModel;
                if (((CheckBox) v).isChecked()) {
                    composeViewModel.updateCompose(new ComposeEntity(compose.getAliment_id(), compose.getListe_id(), compose.getQuantite(), compose.getPriorite(), true));
                } else {
                    composeViewModel.updateCompose(new ComposeEntity(compose.getAliment_id(), compose.getListe_id(), compose.getQuantite(), compose.getPriorite(), false));
                }
            }
        });
    }

    public void bind(AlimentEntity aliment, ComposeEntity compose) {
        this.alimentNom.setText(aliment.getNom());
        this.alimentCheckbox.setChecked(compose.getCoche());
        this.compose = compose;
        Log.d("DEBUG-MATTHIEU", new Boolean(compose.getCoche()).toString());
        switch (aliment.getCategorie()) {
            case "Céréales":
                this.alimentCategorieImage.setImageResource(R.drawable.ic_wheat_24);
                break;
            case "Boissons":
                this.alimentCategorieImage.setImageResource(R.drawable.ic_baseline_wine_bar_24);
                break;
            case "Viande et poisson":
                this.alimentCategorieImage.setImageResource(R.drawable.ic_fish_food_24);
                break;
            case "Légumes et fruits":
                this.alimentCategorieImage.setImageResource(R.drawable.ic_pear_24);
                break;
            case "Produits sucrés":
                this.alimentCategorieImage.setImageResource(R.drawable.ic_cookie_24);
                break;
            case "Produits laitiers":
                this.alimentCategorieImage.setImageResource(R.drawable.ic_milk_bottle_24);
                break;
            case "Plats composés":
                this.alimentCategorieImage.setImageResource(R.drawable.ic_baseline_dinner_dining_24);
                break;
            case "Autre":
                this.alimentCategorieImage.setImageResource(R.drawable.ic_baseline_local_dining_24);
                break;
            default:
                this.alimentCategorieImage.setImageResource(R.drawable.ic_baseline_local_dining_24);
                break;
        }
    }

    public static ContentViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_item, parent, false);
        return new ContentViewHolder(view);
    }

}
