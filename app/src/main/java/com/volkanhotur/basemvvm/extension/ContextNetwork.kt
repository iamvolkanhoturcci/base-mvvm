package com.volkanhotur.basemvvm.extension

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.telephony.TelephonyManager
import androidx.annotation.RequiresPermission

/**
 * @author volkanhotur
 */

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isInternetAvailable(): Boolean {
    try {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.networkPreference
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