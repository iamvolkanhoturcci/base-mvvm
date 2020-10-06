package com.volkanhotur.basemvvm.utils.impl

import android.app.Dialog

/**
 * @author volkanhotur
 */

interface IPickerSelection<T> {
    fun onSelectedItem(data: T?, dialog: Dialog?)
}