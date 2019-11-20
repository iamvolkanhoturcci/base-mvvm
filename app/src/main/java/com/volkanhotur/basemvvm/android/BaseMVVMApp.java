package com.volkanhotur.basemvvm.android;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;

import com.volkanhotur.basemvvm.android.di.AppComponent;
import com.volkanhotur.basemvvm.android.di.DaggerAppComponent;
import com.volkanhotur.basemvvm.android.utils.LocaleHelper;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public class BaseMVVMApp extends DaggerApplication implements Application.ActivityLifecycleCallbacks {

    private int activityReferences = 0;
    private boolean isActivityConfigurationsChanged = false;

    private static MutableLiveData<Boolean> isAppOnBackground = new MutableLiveData<>();

    public static MutableLiveData<Boolean> getIsAppOnBackground(){
        return isAppOnBackground.getValue() != null ? isAppOnBackground : new MutableLiveData<>();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
        Timber.plant(new Timber.DebugTree());
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();

        appComponent.inject(this);

        return appComponent;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Timber.tag("ActivityLifecycle").i("onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (++activityReferences == 1 && !isActivityConfigurationsChanged) {
            isAppOnBackground.postValue(false);
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Timber.tag("ActivityLifecycle").i("onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Timber.tag("ActivityLifecycle").i("onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        isActivityConfigurationsChanged = activity.isChangingConfigurations();
        if (--activityReferences == 0 && !isActivityConfigurationsChanged) {
            isAppOnBackground.postValue(true);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Timber.tag("ActivityLifecycle").i("onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Timber.tag("ActivityLifecycle").i("onActivityDestroyed");
    }
}
