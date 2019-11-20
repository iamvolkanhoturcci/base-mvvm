package com.volkanhotur.basemvvm.android.base.fragment;

import android.content.Context;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import com.core.BaseFragment;
import com.volkanhotur.basemvvm.android.base.exception.UnCaughtExceptionHandler;
import com.volkanhotur.basemvvm.android.base.navigator.NavigatorView;
import com.volkanhotur.basemvvm.android.utils.DefaultClickHandler;

import timber.log.Timber;

public abstract class DefaultFragment<VM extends ViewModel, VDB extends ViewDataBinding> extends BaseFragment<VM, VDB> implements DefaultClickHandler {

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
    public void onClick(View view) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Timber.d("onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Timber.d("onDetach");
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
