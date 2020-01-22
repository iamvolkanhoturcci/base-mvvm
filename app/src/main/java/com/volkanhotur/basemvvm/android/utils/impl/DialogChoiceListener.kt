package com.volkanhotur.basemvvm.android.utils.impl

import android.app.Dialog

interface DialogChoiceListener {

    fun positiveClickListener(dialog: Dialog?)

    fun negativeClickListener(dialog: Dialog?)
}