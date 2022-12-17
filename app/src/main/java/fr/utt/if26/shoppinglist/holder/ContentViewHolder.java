package fr.utt.if26.shoppinglist.holder;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
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
    private final EditText alimentQuantite;
    private final ImageButton alimentDelete;

    private ComposeEntity compose;

    private ComposeViewModel composeViewModel;

    private ContentViewHolder(View itemView) {
        super(itemView);
        alimentNom = itemView.findViewById(R.id.content_item_tv1);
        alimentCategorieImage = (ImageView) itemView.findViewById(R.id.content_item_iv);
        alimentCheckbox = (CheckBox) itemView.findViewById(R.id.content_item_cb);
        alimentQuantite = (EditText) itemView.findViewById(R.id.content_item_et1);
        alimentDelete = (ImageButton) itemView.findViewById(R.id.content_item_ib);
        composeViewModel = ListeContentActivity.getComposeViewModel();
        compose = null;
        alimentCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateComposeList();
            }
        });
        alimentQuantite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (!alimentQuantite.getText().toString().equals("") && !alimentQuantite.getText().toString().equals("0")) {
                    updateComposeList();
                }
            }
        });
        alimentDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                composeViewModel.deleteComposeById(compose.getAliment_id(), compose.getListe_id());
            }
        });
    }

    public void bind(AlimentEntity aliment, ComposeEntity compose) {
        this.alimentNom.setText(aliment.getNom());
        this.alimentCheckbox.setChecked(compose.getCoche());
        this.compose = compose;
        this.alimentQuantite.setText(String.valueOf(compose.getQuantite()));
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

    public void updateComposeList() {
        ListeContentActivity.updatedComposeEntities.removeIf(updatedCompose -> updatedCompose.getListe_id() == compose.getListe_id() && updatedCompose.getAliment_id() == compose.getAliment_id());
        ComposeEntity newCompose = new ComposeEntity(compose.getAliment_id(), compose.getListe_id(), Integer.valueOf(alimentQuantite.getText().toString()), compose.getPriorite(), alimentCheckbox.isChecked());
        ListeContentActivity.updatedComposeEntities.add(newCompose);
    }

    public static ContentViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_item, parent, false);
        return new ContentViewHolder(view);
    }

}
