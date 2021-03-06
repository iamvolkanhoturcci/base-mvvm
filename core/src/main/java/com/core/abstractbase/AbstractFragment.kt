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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.core.R
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject

/**
 * @author volkanhotur
 */
@Suppress("UNCHECKED_CAST")
abstract class AbstractFragment<VM : ViewModel, VDB : ViewDataBinding> : DaggerFragment(), AbstractView {
    @JvmField
    @Inject
    var viewModelFactory: ViewModelProvider.Factory? = null

    private var binding: VDB? = null

    private var viewModel: VM? = null

    private var dialog: AlertDialog? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)

        binding?.lifecycleOwner = this

        viewModel = ViewModelProviders
                .of(activity!!, viewModelFactory)[viewModelClass!!]

        onInitialized(savedInstanceState, viewModel as VM, binding)

        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected abstract val viewModelClass: Class<VM>?

    @get:LayoutRes
    protected abstract val layoutResId: Int

    protected abstract fun onInitialized(savedInstanceState: Bundle?, viewModel: VM?, binding: VDB?)

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

    override fun expireSession(errorTitle : String?, errorMessage: String?) {}

    override fun context(): Context? {
        return context
    }

    protected fun getBinding () : VDB?  {
        return binding
    }

    protected fun getViewModel () : VM?  {
        return viewModel
    }
}