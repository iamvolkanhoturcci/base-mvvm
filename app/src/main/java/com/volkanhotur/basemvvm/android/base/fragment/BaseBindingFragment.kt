package com.volkanhotur.basemvvm.android.base.fragment

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.core.abstractbase.AbstractBindingFragment
import com.volkanhotur.basemvvm.android.base.exception.UnCaughtExceptionHandler
import com.volkanhotur.basemvvm.android.base.navigator.NavigatorView
import com.volkanhotur.basemvvm.android.utils.impl.DefaultClickHandler

/**
 * @author volkanhotur
 */
abstract class BaseBindingFragment<VDB : ViewDataBinding?> : AbstractBindingFragment<VDB>(), DefaultClickHandler {

    private var navigator: NavigatorView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context !is NavigatorView) {
            throw RuntimeException("Context must be instance of NavigatorView")
        }

        navigator = context
    }

    override fun onStart() {
        super.onStart()

        activity?.let {
            Thread.setDefaultUncaughtExceptionHandler(UnCaughtExceptionHandler(it))
        }
    }

    override fun navigateView() {}

    override fun expireSession() {}

    override val isLoadingBarEnabled: Boolean
        get() = false
}