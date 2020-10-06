package com.volkanhotur.basemvvm.base.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.volkanhotur.basemvvm.base.exception.UnCaughtExceptionHandler
import com.core.abstractbase.AbstractBottomSheetBindingDialog
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.volkanhotur.basemvvm.R

/**
 * @author volkanhotur
 */

abstract class BaseBindingDialog<VDB : ViewDataBinding> : AbstractBottomSheetBindingDialog<VDB>() {

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
                    mBehavior.peekHeight = it.resources.displayMetrics.heightPixels
                    mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    
                }
            }
        }
    }

    override fun navigateView() {}

    override fun expireSession(errorTitle : String?, errorMessage: String?) {}

    override val isLoadingBarEnabled: Boolean
        get() = true
}