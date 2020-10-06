package com.volkanhotur.basemvvm.extension

import android.text.format.DateUtils
import java.util.*

/**
 * @author volkanhotur
 */

fun Date.isToday(): Boolean {
    return DateUtils.isToday(this.time)
}