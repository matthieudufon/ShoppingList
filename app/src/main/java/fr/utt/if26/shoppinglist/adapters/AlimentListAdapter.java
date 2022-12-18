package fr.utt.if26.shoppinglist.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import fr.utt.if26.shoppinglist.entities.AlimentEntity;
import fr.utt.if26.shoppinglist.holder.AlimentViewHolder;

public class AlimentListAdapter extends ListAdapter<AlimentEntity, AlimentViewHolder> {

    public AlimentListAdapter(@NonNull DiffUtil.ItemCallback<AlimentEntity> diffCallback) {
        super(diffCallback);
    }

    @Override
    public AlimentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return AlimentViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull AlimentViewHolder holder, int position) {
        AlimentEntity aliment = getItem(position);
        holder.bind(aliment);
    }

    public static class AlimentDiff extends DiffUtil.ItemCallback<AlimentEntity> {

        @Override
        public boolean areItemsTheSame(@NonNull AlimentEntity oldItem, @NonNull AlimentEntity newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull AlimentEntity oldItem, @NonNull AlimentEntity newItem) {
            return oldItem.getNom().equals(newItem.getNom()) &&
                    oldItem.getCategorie().equals(newItem.getCategorie()) &&
                    oldItem.getId().equals(newItem.getId());
        }
    }

}
