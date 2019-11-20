package com.core;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import dagger.android.support.DaggerFragment;

public abstract class BaseBindingFragment<VDB extends ViewDataBinding> extends DaggerFragment implements BaseView {

    protected VDB binding;

    private AlertDialog dialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false);

        onInitialized(savedInstanceState, binding);

        return binding.getRoot();
    }

    protected abstract void onInitialized(Bundle savedInstanceState, VDB binding);

    @LayoutRes
    protected abstract int getLayoutResId();

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
        return getContext();
    }

    public VDB getBinding() {
        return binding;
    }
}
