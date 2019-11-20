package com.core;

import androidx.lifecycle.LifecycleOwner;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import android.view.WindowManager;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseBindingActivity<VDB extends ViewDataBinding> extends DaggerAppCompatActivity implements BaseView, LifecycleOwner {

    private AlertDialog dialog;

    private VDB binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutResId());
        binding.setLifecycleOwner(this);

        onInitialized(savedInstanceState, binding);
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void onInitialized(Bundle instance, VDB binding);

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
}