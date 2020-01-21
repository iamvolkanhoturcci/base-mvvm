package com.core.abstractbase

import android.content.Context

/**
 * @author volkanhotur
 */
interface AbstractView {

    fun showLoadingBar()

    fun hideLoadingBar()

    val isLoadingBarEnabled: Boolean

    fun expireSession()

    fun navigateView()

    fun context(): Context?
}