package com.core.abstractbase

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.core.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * @author volkanhotur
 */
@Suppress("UNCHECKED_CAST")
abstract class AbstractActivity<VM : ViewModel, VDB : ViewDataBinding> : DaggerAppCompatActivity(), AbstractView, LifecycleOwner {

    @JvmField
    @Inject
    var viewModelFactory: ViewModelProvider.Factory? = null

    private var dialog: AlertDialog? = null

    private var viewModel: VM? = null

    private var binding: VDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutResId)

        binding?.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this, viewModelFactory)[viewModelClass!!]

        onInitialized(savedInstanceState, viewModel, binding)
    }

    protected abstract val viewModelClass: Class<VM>?

    @get:LayoutRes
    protected abstract val layoutResId: Int

    protected abstract fun onInitialized(instance: Bundle?, viewModel: VM?, binding: VDB?)

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
        return this
    }

    protected fun getBinding () : VDB?  {
        return binding
    }

    protected fun getViewModel () : VM?  {
        return viewModel
    }
}