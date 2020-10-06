package com.volkanhotur.basemvvm.utils.impl

import com.afollestad.materialdialogs.MaterialDialog

/**
 * @author volkanhotur
 */

interface BottomSheetSelectedItemListener {
    fun selectedItem (selectedIndex: Int?, selectedItem: String?, dialog: MaterialDialog?)
}