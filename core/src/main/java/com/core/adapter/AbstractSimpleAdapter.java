package com.core.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author volkanhotur
 */

public abstract class AbstractSimpleAdapter<VH extends AbstractSimpleHolder, Data> extends RecyclerView.Adapter<AbstractSimpleHolder> {

    private List<Data> items;

    @NonNull
    @Override
    public AbstractSimpleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AbstractSimpleHolder abstractSimpleHolder, int i) {
        abstractSimpleHolder.bind(items.get(i));
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }

        return items.size();
    }
}
