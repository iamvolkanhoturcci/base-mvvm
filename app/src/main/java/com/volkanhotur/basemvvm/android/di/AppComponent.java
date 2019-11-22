package com.volkanhotur.basemvvm.android.di;

import android.app.Application;

import com.volkanhotur.basemvvm.android.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author volkanhotur
 */

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
