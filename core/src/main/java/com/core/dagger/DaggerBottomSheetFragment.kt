package com.core.dagger

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * @author volkanhotur
 */
open class DaggerBottomSheetFragment : BottomSheetDialogFragment(), HasAndroidInjector {

    @JvmField
    @Inject
    var childFragmentInjector: DispatchingAndroidInjector<Any>? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return childFragmentInjector!!
    }
}