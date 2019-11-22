package com.core.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * @author volkanhotur
 */

public abstract class AbstractSimpleHolder<Data> extends RecyclerView.ViewHolder {
    public AbstractSimpleHolder(@NonNull View itemView) {
        super(itemView);
    }

    abstract void bind(Data data);
}
