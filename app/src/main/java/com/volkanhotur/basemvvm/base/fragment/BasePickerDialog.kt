package com.volkanhotur.basemvvm.base.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.volkanhotur.basemvvm.base.exception.UnCaughtExceptionHandler
import com.volkanhotur.basemvvm.utils.impl.IPickerSelection
import com.core.abstractbase.AbstractBottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.volkanhotur.basemvvm.R

/**
 * @author volkanhotur
 */

abstract class BasePickerDialog<VM : ViewModel, VDB : ViewDataBinding, T> : AbstractBottomSheetDialog<VM, VDB>() {

    private var pickerSelection: IPickerSelection<T>? = null

    protected var title = 0

    protected var filter = false

    protected var data: List<T>? = null

    override fun getTheme(): Int {
        return R.style.BottomSheet_Theme
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(context!!, theme)
    }

    override fun onStart() {
        super.onStart()
        activity?.let {
            Thread.setDefaultUncaughtExceptionHandler(UnCaughtExceptionHandler(it))
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dialog?.let { dialog ->
            dialog.setOnShowListener { _: DialogInterface ->
                activity?.let { it ->
                    val d = dialog as BottomSheetDialog
                    val bottomSheet = d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

                    val mBehavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet!!)
                    mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    val displayMetrics = it.resources.displayMetrics
                    bottomSheet.minimumHeight = displayMetrics.heightPixels
                }
            }
        }
    }

    override fun navigateView() {}

    override fun expireSession(errorTitle : String?, errorMessage: String?) {}

    override val isLoadingBarEnabled: Boolean
        get() = true

    fun showPicker(manager: FragmentManager?, selection: IPickerSelection<T>?) {
        pickerSelection = selection

        if (!isAdded) {
            showNow(manager!!, this.javaClass.simpleName)
        }
    }

    abstract fun setTitle(@StringRes title: Int): BasePickerDialog<*, *, *>?

    abstract fun setData(data: List<T>?): BasePickerDialog<*, *, *>?

    abstract fun filter(enable: Boolean): BasePickerDialog<*, *, *>?
}