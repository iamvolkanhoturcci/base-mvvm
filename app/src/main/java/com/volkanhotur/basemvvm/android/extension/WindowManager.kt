package com.volkanhotur.basemvvm.android.extension

import android.util.DisplayMetrics
import android.view.WindowManager
import kotlin.math.pow
import kotlin.math.sqrt

fun WindowManager.isMobileDevice(): Boolean {
    return getDeviceInch(this) < 7
}

private fun getDeviceInch(windowManager: WindowManager): Double {
    val dm = DisplayMetrics()

    windowManager.defaultDisplay.getMetrics(dm)

    val width = dm.widthPixels
    val height = dm.heightPixels
    val dens = dm.densityDpi

    val wi = width.toDouble() / dens.toDouble()
    val hi = height.toDouble() / dens.toDouble()

    val x = wi.pow(2.0)
    val y = hi.pow(2.0)

    return sqrt(x + y)
}