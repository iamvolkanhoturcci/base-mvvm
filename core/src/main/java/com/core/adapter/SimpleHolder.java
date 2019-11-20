package com.core.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public abstract class SimpleHolder<Data> extends RecyclerView.ViewHolder {
    public SimpleHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(Data data);
}
