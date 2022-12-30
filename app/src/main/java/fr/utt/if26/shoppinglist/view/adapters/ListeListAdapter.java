package fr.utt.if26.shoppinglist.view.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import fr.utt.if26.shoppinglist.view.holder.ListeViewHolder;
import fr.utt.if26.shoppinglist.model.entities.ListeEntity;

public class ListeListAdapter extends ListAdapter<ListeEntity, ListeViewHolder> {

    public ListeListAdapter(@NonNull DiffUtil.ItemCallback<ListeEntity> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ListeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ListeViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ListeViewHolder holder, int position) {
        ListeEntity liste = getItem(position);
        holder.bind(liste);
    }

    public static class ListeDiff extends DiffUtil.ItemCallback<ListeEntity> {

        @Override
        public boolean areItemsTheSame(@NonNull ListeEntity oldItem, @NonNull ListeEntity newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ListeEntity oldItem, @NonNull ListeEntity newItem) {
            return oldItem.getId().equals(newItem.getId());
        }
    }

}
