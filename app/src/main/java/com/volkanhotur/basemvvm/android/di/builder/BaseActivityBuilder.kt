package com.volkanhotur.basemvvm.android.di.builder

import com.volkanhotur.basemvvm.android.di.scope.ActivityScope
import com.volkanhotur.basemvvm.android.ui.screen.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author volkanhotur
 */
@Module
abstract class BaseActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivityInjector(): MainActivity?
}