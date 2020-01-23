package com.volkanhotur.basemvvm.android.base.activity

import android.content.Context
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.core.abstractbase.AbstractBindingActivity
import com.volkanhotur.basemvvm.android.base.exception.UnCaughtExceptionHandler
import com.volkanhotur.basemvvm.android.utils.helper.LocaleHelper
import com.volkanhotur.basemvvm.android.utils.impl.DefaultClickHandler

/**
 * @author volkanhotur
 */
abstract class BaseBindingActivity<VDB : ViewDataBinding?> : AbstractBindingActivity<VDB>(), DefaultClickHandler {

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
        get() = false
}