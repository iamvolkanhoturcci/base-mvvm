package com.volkanhotur.basemvvm.android.base.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import com.core.BaseActivity;
import com.volkanhotur.basemvvm.android.base.exception.UnCaughtExceptionHandler;
import com.volkanhotur.basemvvm.android.utils.DefaultClickHandler;
import com.volkanhotur.basemvvm.android.utils.LocaleHelper;

public abstract class DefaultActivity<VM extends ViewModel, VDB extends ViewDataBinding> extends BaseActivity<VM, VDB> implements DefaultClickHandler {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtExceptionHandler(this));
        super.onCreate(savedInstanceState);
    }

    @Override
    public void navigateView() { }

    @Override
    public void expireSession() { }

    @Override
    public boolean isLoadingBarEnabled() {
        return false;
    }
}
