package com.volkanhotur.basemvvm.android.base.fragment;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.core.BaseBindingFragment;
import com.volkanhotur.basemvvm.android.base.exception.UnCaughtExceptionHandler;
import com.volkanhotur.basemvvm.android.base.navigator.NavigatorView;
import com.volkanhotur.basemvvm.android.utils.impl.DefaultClickHandler;

public abstract class DefaultBindingFragment<VDB extends ViewDataBinding> extends BaseBindingFragment<VDB> implements DefaultClickHandler {

    public NavigatorView navigator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(context instanceof NavigatorView))
            throw new RuntimeException("Context must be instance of NavigatorView");

        navigator = (NavigatorView) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtExceptionHandler(getActivity()));
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
