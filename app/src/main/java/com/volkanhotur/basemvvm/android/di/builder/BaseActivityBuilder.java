package com.volkanhotur.basemvvm.android.di.builder;

import com.volkanhotur.basemvvm.android.ui.main.MainActivity;
import com.volkanhotur.basemvvm.android.di.scope.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author volkanhotur
 */

@Module
public abstract class BaseActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity mainActivityInjector();

}
