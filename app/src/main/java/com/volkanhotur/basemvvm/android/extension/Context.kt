package com.volkanhotur.basemvvm.android.extension

import android.Manifest
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.Uri
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresPermission
import com.google.android.material.snackbar.Snackbar
import com.volkanhotur.alerty.Alerty
import com.volkanhotur.basemvvm.R
import com.volkanhotur.basemvvm.android.utils.impl.DialogChoiceListener
import java.util.*

fun Context.showSnackBar(view: View?, message: String?) {
    view?.let {
        Snackbar.make(view, message ?: "", Snackbar.LENGTH_LONG).show()
    }
}

fun Context.showToastLong(message: String?){
    Toast.makeText(this, message ?: "", Toast.LENGTH_LONG).show();
}

fun Context.showToastShort(message: String?){
    Toast.makeText(this, message ?: "", Toast.LENGTH_SHORT).show();
}

fun Context.openPlayStore() {
    val appPackageName = packageName
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("" + appPackageName)))
    } catch (e: ActivityNotFoundException) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("" + appPackageName)))
    }
}

fun Context.showInfoDialog(title: String?, message: String?, positiveText: String?) {
    Alerty.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancellable(false)
            .setPositiveButtonText(positiveText)
            .setHeaderImage(R.drawable.ic_info_gray)
            .setPositiveButtonColor(-0x7d7d7e)
            .setPositiveButtonTextColor(-0x1)
            .setTitleTextColor(-0x1000000)
            .setMessageTextColor(-0xdededf)
            .setTextAppearance(Alerty.MEDIUM_TEXT)
            .setPositiveListener { obj: Dialog -> obj.dismiss() }
            .setButtonRadius(16f)
            .setDialogRadius(16f)
            .build()
}

fun Context.showChoiceDialog(title: String?, message: String?, positiveText: String?, negativeText: String?, choiceListener: DialogChoiceListener) {
    Alerty.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancellable(false)
            .setPositiveButtonText(positiveText)
            .setNegativeButtonText(negativeText)
            .setHeaderImage(0)
            .setPositiveButtonColor(-0x7d7d7e)
            .setNegativeButtonColor(-0x7d7d7e)
            .setPositiveButtonTextColor(-0x1)
            .setNegativeButtonTextColor(-0x1)
            .setTitleTextColor(-0x1000000)
            .setMessageTextColor(-0xdededf)
            .setTextAppearance(Alerty.MEDIUM_TEXT)
            .setButtonRadius(16f)
            .setDialogRadius(16f)
            .setPositiveListener { dialog: Dialog? -> choiceListener.positiveClickListener(dialog) }
            .setNegativeListener { dialog: Dialog? -> choiceListener.negativeClickListener(dialog) }
            .build()
}

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun isInternetAvailable(context: Context): Boolean {
    try {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkInfo = connectivityManager.getNetworkInfo(network)
        if (networkInfo != null && networkInfo.detailedState == NetworkInfo.DetailedState.CONNECTED) {
            return true
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isConnectedWifi(): Boolean {
    val info = getNetworkInfo(this)
    return info.isConnected && info.type == ConnectivityManager.TYPE_WIFI
}

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isConnectedMobile(): Boolean {
    val info = getNetworkInfo(this)
    return info.isConnected && info.type == ConnectivityManager.TYPE_MOBILE
}

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isConnectedFast(): Boolean {
    val info = getNetworkInfo(this)
    return info.isConnected && isConnectionFast(info.type, info.subtype)
}

private fun isConnectionFast(type: Int, subType: Int): Boolean {
    return when (type) {
        NetworkCapabilities.TRANSPORT_WIFI -> {
            true
        }
        NetworkCapabilities.TRANSPORT_CELLULAR -> {
            when (subType) {
                TelephonyManager.NETWORK_TYPE_EVDO_0,
                TelephonyManager.NETWORK_TYPE_EVDO_A,
                TelephonyManager.NETWORK_TYPE_HSDPA,
                TelephonyManager.NETWORK_TYPE_HSPA,
                TelephonyManager.NETWORK_TYPE_HSUPA,
                TelephonyManager.NETWORK_TYPE_UMTS,
                TelephonyManager.NETWORK_TYPE_EHRPD,
                TelephonyManager.NETWORK_TYPE_EVDO_B,
                TelephonyManager.NETWORK_TYPE_HSPAP,
                TelephonyManager.NETWORK_TYPE_LTE -> true

                TelephonyManager.NETWORK_TYPE_1xRTT,
                TelephonyManager.NETWORK_TYPE_CDMA,
                TelephonyManager.NETWORK_TYPE_EDGE,
                TelephonyManager.NETWORK_TYPE_GPRS,
                TelephonyManager.NETWORK_TYPE_IDEN -> false

                else -> false
            }
        }
        else -> {
            false
        }
    }
}

private fun getNetworkInfo(context: Context): NetworkInfo {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo
}

fun Context.getScreenHeight(): Int {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    return dm.heightPixels
}

fun Context.getScreenWidth(): Int {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    return dm.widthPixels
}

fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}