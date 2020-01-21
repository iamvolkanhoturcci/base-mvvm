package com.volkanhotur.basemvvm.android.extension

import android.app.Activity
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val inputMethodManager = (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
    inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
}