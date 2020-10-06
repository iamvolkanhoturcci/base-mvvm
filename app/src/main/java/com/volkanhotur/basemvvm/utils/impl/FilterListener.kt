package com.volkanhotur.basemvvm.utils.impl

/**
 * @author volkanhotur
 */

interface FilterListener<T> {
    fun onFilterCompleted(data: T)
}