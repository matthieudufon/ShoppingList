package fr.utt.if26.shoppinglist.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import fr.utt.if26.shoppinglist.holder.ListeViewHolder;
import fr.utt.if26.shoppinglist.entities.ListeEntity;

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
        ListeEntity current = getItem(position);
        holder.bind(current.getNom(), current.getLieu(), current.getDate(), current.getId());
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