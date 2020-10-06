package com.volkanhotur.basemvvm.base.fragment

import android.content.Context
import android.view.View
import androidx.databinding.ViewDataBinding
import com.volkanhotur.basemvvm.base.exception.UnCaughtExceptionHandler
import com.volkanhotur.basemvvm.base.navigator.Navigator
import com.volkanhotur.basemvvm.utils.impl.DefaultClickHandler
import com.core.abstractbase.AbstractBindingFragment

/**
 * @author volkanhotur
 */

abstract class BaseBindingFragment<VDB : ViewDataBinding> : AbstractBindingFragment<VDB>(),
    DefaultClickHandler {

    private var navigate: Navigator.Navigate? = null
    private var action: Navigator.Action? = null

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

    override fun navigateView() {}

    override fun expireSession(errorTitle : String?, errorMessage: String?) {

    }

    override val isLoadingBarEnabled: Boolean
        get() = false

    override fun onClick(view: View?) {}
}