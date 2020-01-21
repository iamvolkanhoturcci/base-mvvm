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
@Suppress("UNCHECKED_CAST")
abstract class AbstractBindingFragment<VDB : ViewDataBinding?> : DaggerFragment(), AbstractView {

    private var binding: ViewDataBinding? = null

    private var dialog: AlertDialog? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)

        onInitialized(savedInstanceState, binding as VDB?)

        return binding?.root
    }

    protected abstract fun onInitialized(savedInstanceState: Bundle?, binding: VDB?)
    @get:LayoutRes
    protected abstract val layoutResId: Int

    override fun showLoadingBar() {
        context()?.let {
            dialog?.let {
                if(!dialog!!.isShowing){
                    dialog!!.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                    dialog!!.show()
                }
            }.run {
                dialog = AlertDialog.Builder(it, R.style.DialogStyle)
                        .setView(R.layout.view_progress)
                        .setCancelable(false)
                        .create()
            }
        }
    }

    override fun hideLoadingBar() {
        dialog?.dismiss()
    }

    override fun context(): Context? {
        return context
    }

}