package com.volkanhotur.basemvvm.base.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.volkanhotur.basemvvm.base.exception.UnCaughtExceptionHandler
import com.volkanhotur.basemvvm.utils.helper.LocaleHelper
import com.volkanhotur.basemvvm.utils.impl.DefaultClickHandler
import com.core.abstractbase.AbstractActivity

/**
 * @author volkanhotur
 */

abstract class BaseActivity<VM : ViewModel, VDB : ViewDataBinding> : AbstractActivity<VM, VDB>(), DefaultClickHandler {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.setDefaultUncaughtExceptionHandler(UnCaughtExceptionHandler(this))
        super.onCreate(savedInstanceState)
    }

    override fun navigateView() {}

    override fun expireSession(errorTitle : String?, errorMessage: String?) {
    }

    override val isLoadingBarEnabled: Boolean
        get() = true

    override fun onClick(view: View?) {}
}