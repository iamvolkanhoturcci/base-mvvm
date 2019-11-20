package com.core;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import android.view.WindowManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<VM extends ViewModel, VDB extends ViewDataBinding> extends DaggerAppCompatActivity implements BaseView, LifecycleOwner {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    private AlertDialog dialog;

    protected VM viewModel;

    private VDB binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutResId());
        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(getViewModelClass());

        onInitialized(savedInstanceState, viewModel, binding);
    }

    protected abstract Class<VM> getViewModelClass();

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void onInitialized(Bundle instance, VM viewModel, VDB binding);

    @Override
    public void showLoadingBar() {
        if (dialog == null)
            dialog = new AlertDialog.Builder(context(), R.style.DialogStyle)
                    .setView(R.layout.view_progress)
                    .setCancelable(false)
                    .create();

        if(dialog.getWindow() != null)
            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        if (dialog.isShowing())
            return;

        dialog.show();
    }

    @Override
    public void hideLoadingBar() {
        dialog.dismiss();
    }

    @Override
    public Context context() {
        return this;
    }

    public VDB getBinding() {
        return binding;
    }

    public VM getViewModel() {
        return viewModel;
    }
}