package com.volkanhotur.basemvvm.android.utils.impl

import android.app.Dialog

/**
 * @author volkanhotur
 */
interface IPickerSelection<T> {
    fun onSelectedItem(data: T?, dialog: Dialog?)
}