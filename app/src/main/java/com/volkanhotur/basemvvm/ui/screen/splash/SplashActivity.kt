package com.volkanhotur.basemvvm.ui.screen.splash

import android.os.Bundle
import android.view.View
import com.volkanhotur.basemvvm.R
import com.volkanhotur.basemvvm.base.activity.BaseActivity
import com.volkanhotur.basemvvm.databinding.ActivitySplashBinding

/**
 * @author volkanhotur
 */

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    override val viewModelClass: Class<SplashViewModel>
        get() = SplashViewModel::class.java

    override val layoutResId: Int
        get() = R.layout.activity_splash

    override fun onInitialized(instance: Bundle?, viewModel: SplashViewModel?, binding: ActivitySplashBinding?) {

    }

    override fun showLoadingBar() {
        getBinding()?.progressBar?.visibility = View.VISIBLE
    }

    override fun hideLoadingBar() {
        getBinding()?.progressBar?.visibility = View.GONE
    }

    /********************* START : This is an exception case ***********************/
    override fun expireSession(errorTitle: String?, errorMessage: String?) {

    }

    override fun navigateView() {

    }
    /********************* END : This is an exception case *************************/
}