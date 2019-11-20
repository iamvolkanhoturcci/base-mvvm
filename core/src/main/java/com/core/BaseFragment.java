package com.core;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
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

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<VM extends ViewModel, VDB extends ViewDataBinding> extends DaggerFragment implements BaseView {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    protected VDB binding;

    protected VM viewModel;

    private AlertDialog dialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false);
        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders
                .of(Objects.requireNonNull(getActivity()), viewModelFactory)
                .get(getViewModelClass());

        onInitialized(savedInstanceState, viewModel, binding);

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract Class<VM> getViewModelClass();

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void onInitialized(Bundle savedInstanceState, VM viewModel, VDB binding);

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
    public void expireSession() { }

    public VDB getBinding() {
        return binding;
    }

    public VM getViewModel() {
        return viewModel;
    }

    @Override
    public Context context() {
        return getContext();
    }
}
