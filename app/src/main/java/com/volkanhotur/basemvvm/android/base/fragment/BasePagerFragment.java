package com.volkanhotur.basemvvm.android.base.fragment;

import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import com.core.abstractbase.AbstractFragment;
import com.volkanhotur.basemvvm.android.base.exception.UnCaughtExceptionHandler;
import com.volkanhotur.basemvvm.android.utils.impl.DefaultClickHandler;

import timber.log.Timber;

/**
 * @author volkanhotur
 */

public abstract class BasePagerFragment<VM extends ViewModel, VDB extends ViewDataBinding> extends AbstractFragment<VM, VDB> implements DefaultClickHandler {

    @Override
    public void onStart() {
        super.onStart();
        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtExceptionHandler(getActivity()));
    }

    @Override
    public void onClick(View view) { }

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
