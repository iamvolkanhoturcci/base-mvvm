package com.volkanhotur.basemvvm.android.extension

import android.text.format.DateUtils
import java.util.*

fun Date.isToday(): Boolean {
    return DateUtils.isToday(this.time)
}