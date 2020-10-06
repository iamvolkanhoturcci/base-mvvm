package com.volkanhotur.basemvvm.utils.impl

import android.app.Dialog

/**
 * @author volkanhotur
 */

interface DialogChoiceListener {

    fun positiveClickListener(dialog: Dialog?)

    fun negativeClickListener(dialog: Dialog?)
}