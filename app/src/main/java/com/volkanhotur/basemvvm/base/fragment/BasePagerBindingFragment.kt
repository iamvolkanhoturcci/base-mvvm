package com.volkanhotur.basemvvm.base.fragment

import android.view.View
import androidx.databinding.ViewDataBinding
import com.volkanhotur.basemvvm.base.exception.UnCaughtExceptionHandler
import com.volkanhotur.basemvvm.utils.impl.DefaultClickHandler
import com.core.abstractbase.AbstractBindingFragment

/**
 * @author volkanhotur
 */

abstract class BasePagerBindingFragment<VDB : ViewDataBinding> : AbstractBindingFragment<VDB>(), DefaultClickHandler {

    override fun onStart() {
        super.onStart()

        activity?.let {
            Thread.setDefaultUncaughtExceptionHandler(UnCaughtExceptionHandler(it))
        }
    }

    override fun onClick(view: View?) {}

    override fun navigateView() {}

    override fun expireSession(errorTitle : String?, errorMessage: String?) {}

    override val isLoadingBarEnabled: Boolean
        get() = true
}