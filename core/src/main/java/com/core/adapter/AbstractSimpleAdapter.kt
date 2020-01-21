package com.core.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author volkanhotur
 */
abstract class AbstractSimpleAdapter<VH : AbstractSimpleHolder<*>?, Data> : RecyclerView.Adapter<AbstractSimpleHolder<*>>() {

    private val items: List<Data>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractSimpleHolder<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(abstractSimpleHolder: AbstractSimpleHolder<*>, i: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }
}