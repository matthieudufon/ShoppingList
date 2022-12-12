package fr.utt.if26.shoppinglist.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.HashMap;

import fr.utt.if26.shoppinglist.entities.AlimentAndCompose;
import fr.utt.if26.shoppinglist.entities.AlimentEntity;
import fr.utt.if26.shoppinglist.entities.ComposeEntity;
import fr.utt.if26.shoppinglist.holder.ContentViewHolder;

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
        ComposeEntity compose = getItem(position).compose;
        holder.bind(aliment, compose);
    }

    public static class ListeDiff extends DiffUtil.ItemCallback<AlimentAndCompose> {

        @Override
        public boolean areItemsTheSame(@NonNull AlimentAndCompose oldItem, @NonNull AlimentAndCompose newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull AlimentAndCompose oldItem, @NonNull AlimentAndCompose newItem) {
            //return oldItem.keySet().equals(newItem.keySet()) && oldItem.values().equals(newItem.values());
            return oldItem.aliment.getId().equals(newItem.aliment.getId()) && oldItem.compose.getListe_id().equals(newItem.compose.getListe_id());
        }
    }

}
