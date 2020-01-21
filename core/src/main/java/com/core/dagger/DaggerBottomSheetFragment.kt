package com.core.dagger

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * @author volkanhotur
 */
open class DaggerBottomSheetFragment : BottomSheetDialogFragment(), HasSupportFragmentInjector {

    @JvmField
    @Inject
    var childFragmentInjector: DispatchingAndroidInjector<Fragment>? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return childFragmentInjector!!
    }
}