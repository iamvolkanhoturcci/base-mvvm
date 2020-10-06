package com.core.abstractbase

import android.content.Context

/**
 * @author volkanhotur
 */
interface AbstractView {

    fun showLoadingBar()

    fun hideLoadingBar()

    val isLoadingBarEnabled: Boolean

    fun expireSession(errorTitle : String?, errorMessage: String?)

    fun navigateView()

    fun context(): Context?
}