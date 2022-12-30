package fr.utt.if26.shoppinglist.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fr.utt.if26.shoppinglist.view.fragmentsMainActivity.AlimentsFragment;
import fr.utt.if26.shoppinglist.R;
import fr.utt.if26.shoppinglist.model.entities.AlimentEntity;
import fr.utt.if26.shoppinglist.viewModels.AlimentViewModel;

public class AlimentViewHolder extends RecyclerView.ViewHolder {

    private final TextView alimentItemViewNom;
    private final TextView alimentItemViewCategorie;
    private final ImageView alimentItemViewCategorieImage;
    private final ImageButton alimentItemDeleteButton;
    private AlimentViewModel alimentViewModel;
    private int id;

    public AlimentViewHolder(@NonNull View itemView) {
        super(itemView);
        alimentItemViewNom = itemView.findViewById(R.id.aliment_item_tv1);
        alimentItemViewCategorie = itemView.findViewById(R.id.aliment_item_tv2);
        alimentItemViewCategorieImage = itemView.findViewById(R.id.aliment_item_iv);
        alimentItemDeleteButton = itemView.findViewById(R.id.aliment_item_ib);
        alimentViewModel = AlimentsFragment.getAlimentViewModel();
        alimentItemDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alimentViewModel.deleteAlimentById(id);
            }
        });
    }

    public void bind(AlimentEntity aliment) {
        alimentItemViewNom.setText(aliment.getNom());
        alimentItemViewCategorie.setText(aliment.getCategorie());
        this.id = aliment.getId();
        switch (aliment.getCategorie()) {
            case "Céréales":
                this.alimentItemViewCategorieImage.setImageResource(R.drawable.ic_wheat_24);
                break;
            case "Boissons":
                this.alimentItemViewCategorieImage.setImageResource(R.drawable.ic_baseline_wine_bar_24);
                break;
            case "Viande et poisson":
                this.alimentItemViewCategorieImage.setImageResource(R.drawable.ic_fish_food_24);
                break;
            case "Légumes et fruits":
                this.alimentItemViewCategorieImage.setImageResource(R.drawable.ic_pear_24);
                break;
            case "Produits sucrés":
                this.alimentItemViewCategorieImage.setImageResource(R.drawable.ic_cookie_24);
                break;
            case "Produits laitiers":
                this.alimentItemViewCategorieImage.setImageResource(R.drawable.ic_milk_bottle_24);
                break;
            case "Plats composés":
                this.alimentItemViewCategorieImage.setImageResource(R.drawable.ic_baseline_dinner_dining_24);
                break;
            case "Autre":
                this.alimentItemViewCategorieImage.setImageResource(R.drawable.ic_baseline_local_dining_24);
                break;
            default:
                this.alimentItemViewCategorieImage.setImageResource(R.drawable.ic_baseline_local_dining_24);
                break;
        }
    }

    public static AlimentViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aliments_item, parent, false);
        return new AlimentViewHolder(view);
    }

}
