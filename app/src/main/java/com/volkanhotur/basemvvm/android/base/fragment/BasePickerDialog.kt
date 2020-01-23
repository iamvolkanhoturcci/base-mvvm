package com.volkanhotur.basemvvm.android.base.fragment

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.FrameLayout
import androidx.annotation.StringRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.core.abstractbase.AbstractBottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.volkanhotur.basemvvm.R
import com.volkanhotur.basemvvm.android.base.exception.UnCaughtExceptionHandler
import com.volkanhotur.basemvvm.android.utils.impl.IPickerSelection
import java.util.*

/**
 * @author volkanhotur
 */
abstract class BasePickerDialog<VM : ViewModel?, VDB : ViewDataBinding?, T> : AbstractBottomSheetDialog<VM, VDB>() {

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

        dialog?.let {
            it.setOnShowListener { dialog: DialogInterface ->
                activity?.let {
                    val d = dialog as BottomSheetDialog
                    val bottomSheet = d.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)

                    val mBehavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(Objects.requireNonNull(bottomSheet))
                    mBehavior.state = BottomSheetBehavior.STATE_EXPANDED

                    val displayMetrics = it.resources.displayMetrics

                    bottomSheet?.let {
                        it.minimumHeight = displayMetrics.heightPixels
                    }
                }
            }
        }
    }

    override fun getContext(): Context? {
        return activity
    }

    override fun navigateView() {}

    override fun expireSession() {}

    override val isLoadingBarEnabled: Boolean
        get() = false

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