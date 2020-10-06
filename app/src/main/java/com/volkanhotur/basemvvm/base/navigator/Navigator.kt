package com.volkanhotur.basemvvm.base.navigator

import android.content.Intent
import androidx.fragment.app.Fragment

/**
 * @author volkanhotur
 */

class Navigator {
    interface Navigate {
        fun pushFragment(fragment: Fragment?)

        fun popFragment()

        fun popFragments(dept: Int)

        fun redirectFragment(itemId: Int?)
    }

    interface Action {
        fun switchTab(tab: Int)

        fun returnRoot()

        fun openActivity(intent: Intent?)
    }
}