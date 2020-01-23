package com.volkanhotur.basemvvm.android.base.fragment

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.core.abstractbase.AbstractFragment
import com.volkanhotur.basemvvm.android.base.exception.UnCaughtExceptionHandler
import com.volkanhotur.basemvvm.android.utils.impl.DefaultClickHandler
import timber.log.Timber

/**
 * @author volkanhotur
 */
abstract class BasePagerFragment<VM : ViewModel?, VDB : ViewDataBinding?> : AbstractFragment<VM, VDB>(), DefaultClickHandler {

    override fun onStart() {
        super.onStart()

        activity?.let {
            Thread.setDefaultUncaughtExceptionHandler(UnCaughtExceptionHandler(it))
        }
    }

    override fun onClick(view: View?) {}

    override fun navigateView() {}

    override fun expireSession() {}

    override val isLoadingBarEnabled: Boolean
        get() = false
}