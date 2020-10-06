package com.volkanhotur.basemvvm.extension

import android.content.res.Resources
import kotlin.math.roundToInt

/**
 * @author volkanhotur
 */

fun Float.pxToDp() : Float {
    val densityDpi = Resources.getSystem().displayMetrics.densityDpi.toFloat()
    return this / (densityDpi / 160f)
}

fun Float.dpToPx(): Int {
    val density = Resources.getSystem().displayMetrics.density
    return (this * density).roundToInt()
}