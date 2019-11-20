package com.core.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class SimpleAdapter<VH extends SimpleHolder, Data> extends RecyclerView.Adapter<SimpleHolder> {

    private List<Data> items;

    @NonNull
    @Override
    public SimpleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleHolder simpleHolder, int i) {
        simpleHolder.bind(items.get(i));
    }

    @Override
    public int getItemCount() {

        if (items == null)
            return 0;

        return items.size();
    }
}
