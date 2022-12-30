package fr.utt.if26.shoppinglist.view.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import fr.utt.if26.shoppinglist.model.entities.embedded.AlimentAndCompose;
import fr.utt.if26.shoppinglist.model.entities.AlimentEntity;
import fr.utt.if26.shoppinglist.model.entities.ComposeEntity;
import fr.utt.if26.shoppinglist.view.holder.ContentViewHolder;

public class ContentListAdapter extends ListAdapter<AlimentAndCompose, ContentViewHolder> {

    public ContentListAdapter(@NonNull DiffUtil.ItemCallback<AlimentAndCompose> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ContentViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        AlimentEntity aliment = getItem(position).aliment;
        ComposeEntity compose = getItem(position).composes.get(0);
        holder.bind(aliment, compose);
    }

    public static class ListeDiff extends DiffUtil.ItemCallback<AlimentAndCompose> {

        @Override
        public boolean areItemsTheSame(@NonNull AlimentAndCompose oldItem, @NonNull AlimentAndCompose newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull AlimentAndCompose oldItem, @NonNull AlimentAndCompose newItem) {
            return oldItem.composes.get(0).getListe_id().equals(newItem.composes.get(0).getListe_id()) &&
                    oldItem.composes.get(0).getAliment_id().equals(newItem.composes.get(0).getAliment_id()) &&
                    oldItem.composes.get(0).getCoche() == newItem.composes.get(0).getCoche() &&
                    oldItem.composes.get(0).getPriorite() == newItem.composes.get(0).getPriorite() &&
                    oldItem.composes.get(0).getQuantite() == newItem.composes.get(0).getQuantite() &&
                    oldItem.aliment.getCategorie().equals(newItem.aliment.getCategorie()) &&
                    oldItem.aliment.getNom().equals(newItem.aliment.getNom()) &&
                    oldItem.aliment.getId().equals(newItem.aliment.getId());
        }
    }

}
