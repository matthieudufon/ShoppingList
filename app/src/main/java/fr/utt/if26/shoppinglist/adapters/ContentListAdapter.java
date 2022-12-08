package fr.utt.if26.shoppinglist.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import fr.utt.if26.shoppinglist.entities.AlimentEntity;
import fr.utt.if26.shoppinglist.holder.ContentViewHolder;

public class ContentListAdapter extends ListAdapter<AlimentEntity, ContentViewHolder> {

    public ContentListAdapter(@NonNull DiffUtil.ItemCallback<AlimentEntity> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ContentViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        AlimentEntity aliment = getItem(position);
        holder.bind(aliment);
    }

    public static class ListeDiff extends DiffUtil.ItemCallback<AlimentEntity> {

        @Override
        public boolean areItemsTheSame(@NonNull AlimentEntity oldItem, @NonNull AlimentEntity newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull AlimentEntity oldItem, @NonNull AlimentEntity newItem) {
            return oldItem.getId().equals(newItem.getId());
        }
    }

}
