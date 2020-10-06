package com.core.abstractbase

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.core.R
import dagger.android.support.DaggerFragment

/**
 * @author volkanhotur
 */

abstract class AbstractBindingFragment<VDB : ViewDataBinding> : DaggerFragment(), AbstractView {

    private var binding: VDB? = null

    private var dialog: AlertDialog? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)

        onInitialized(savedInstanceState, binding)

        return binding?.root
    }

    protected abstract fun onInitialized(savedInstanceState: Bundle?, binding: VDB?)

    @get:LayoutRes
    protected abstract val layoutResId: Int

    override fun showLoadingBar() {
        context()?.let {
            dialog?.let {
                if(!it.isShowing){
                    it.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                    it.show()
                }
            }.run {
                dialog = AlertDialog.Builder(it, R.style.DialogStyle)
                    .setView(R.layout.view_progress)
                    .setCancelable(false)
                    .create()
                dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                dialog?.show()
            }
        }
    }

    override fun hideLoadingBar() {
        dialog?.dismiss()
    }

    override fun context(): Context? {
        return context
    }

    protected fun getBinding () : VDB?  {
        return binding
    }
}