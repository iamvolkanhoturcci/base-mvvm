package com.volkanhotur.basemvvm.android.base.activity

import android.content.Context
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.core.abstractbase.AbstractActivity
import com.volkanhotur.basemvvm.android.base.exception.UnCaughtExceptionHandler
import com.volkanhotur.basemvvm.android.extension.showChoiceDialog
import com.volkanhotur.basemvvm.android.utils.helper.LocaleHelper
import com.volkanhotur.basemvvm.android.utils.impl.DefaultClickHandler

/**
 * @author volkanhotur
 */
abstract class BaseActivity<VM : ViewModel?, VDB : ViewDataBinding?> : AbstractActivity<VM, VDB>(), DefaultClickHandler {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.setDefaultUncaughtExceptionHandler(UnCaughtExceptionHandler(this))
        super.onCreate(savedInstanceState)
    }

    override fun navigateView() {}

    override fun expireSession() {}

    override val isLoadingBarEnabled: Boolean
        get() = true
}