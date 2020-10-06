package com.volkanhotur.basemvvm.base.fragment

import android.content.Context
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.volkanhotur.basemvvm.base.exception.UnCaughtExceptionHandler
import com.volkanhotur.basemvvm.base.navigator.Navigator
import com.volkanhotur.basemvvm.utils.impl.DefaultClickHandler
import com.core.abstractbase.AbstractFragment

/**
 * @author volkanhotur
 */

abstract class BaseFragment<VM : ViewModel, VDB : ViewDataBinding> : AbstractFragment<VM, VDB>(),
    DefaultClickHandler {

    var navigate: Navigator.Navigate? = null
    var action: Navigator.Action? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Navigator.Navigate) navigate = context

        if (context is Navigator.Action) action = context
    }

    override fun onStart() {
        super.onStart()

        activity?.let {
            Thread.setDefaultUncaughtExceptionHandler(UnCaughtExceptionHandler(it))
        }
    }

    override fun onClick(view: View?) {}

    override fun navigateView() {}

    override fun expireSession(errorTitle : String?, errorMessage: String?) {

    }

    override val isLoadingBarEnabled: Boolean
        get() = true
}