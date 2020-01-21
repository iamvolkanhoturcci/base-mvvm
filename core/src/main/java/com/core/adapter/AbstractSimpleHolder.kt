package com.core.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author volkanhotur
 */
abstract class AbstractSimpleHolder<Data>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(data: Data)
}