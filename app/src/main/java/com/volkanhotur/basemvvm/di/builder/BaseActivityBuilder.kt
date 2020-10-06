package com.volkanhotur.basemvvm.di.builder

import com.volkanhotur.basemvvm.di.scope.ActivityScope
import com.volkanhotur.basemvvm.ui.screen.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author volkanhotur
 */

@Module
abstract class BaseActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun splashActivityInjector(): SplashActivity?
}