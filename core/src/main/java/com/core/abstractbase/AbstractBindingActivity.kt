package com.core.abstractbase

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.core.R
import dagger.android.support.DaggerAppCompatActivity

/**
 * @author volkanhotur
 */
@Suppress("UNCHECKED_CAST")
abstract class AbstractBindingActivity<VDB : ViewDataBinding?> : DaggerAppCompatActivity(), AbstractView, LifecycleOwner {
    private var dialog: AlertDialog? = null

    private var binding: ViewDataBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutResId)

        binding!!.lifecycleOwner = this

        onInitialized(savedInstanceState, binding as VDB?)
    }

    @get:LayoutRes
    protected abstract val layoutResId: Int

    protected abstract fun onInitialized(instance: Bundle?, binding: VDB?)

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
        return this
    }

}