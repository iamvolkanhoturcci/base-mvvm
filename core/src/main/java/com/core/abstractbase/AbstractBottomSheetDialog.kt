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
import com.core.dagger.DaggerBottomSheetFragment
import javax.inject.Inject

/**
 * @author volkanhotur
 */
@Suppress("UNCHECKED_CAST")
abstract class AbstractBottomSheetDialog<VM : ViewModel?, VDB : ViewDataBinding?> : DaggerBottomSheetFragment(), AbstractView {

    private var dialog: AlertDialog? = null

    @JvmField
    @Inject
    var viewModelFactory: ViewModelProvider.Factory? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutResId, container, false)

        val viewModel: VM = ViewModelProviders
                .of(this, viewModelFactory)
                .get(viewModel!!)
        onInitialized(savedInstanceState, viewModel as VM, binding as VDB)

        return binding.root
    }

    protected abstract val viewModel: Class<VM>?

    @get:LayoutRes
    protected abstract val layoutResId: Int

    protected abstract fun onInitialized(savedInstanceState: Bundle?, viewModel: VM, binding: VDB)

    override fun getContext(): Context? {
        return activity
    }

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
}