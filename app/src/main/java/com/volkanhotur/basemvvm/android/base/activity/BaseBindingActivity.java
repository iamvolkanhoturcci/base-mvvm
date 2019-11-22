package com.volkanhotur.basemvvm.android.base.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.core.abstractbase.AbstractBindingActivity;
import com.volkanhotur.basemvvm.android.base.exception.UnCaughtExceptionHandler;
import com.volkanhotur.basemvvm.android.utils.impl.DefaultClickHandler;
import com.volkanhotur.basemvvm.android.utils.helper.LocaleHelper;

/**
 * @author volkanhotur
 */

public abstract class BaseBindingActivity< VDB extends ViewDataBinding> extends AbstractBindingActivity< VDB> implements DefaultClickHandler {

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
    public void expireSession() {}

    @Override
    public boolean isLoadingBarEnabled() {
        return false;
    }
}
