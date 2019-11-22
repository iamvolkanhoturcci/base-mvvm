package com.core.abstractbase;

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

import com.core.R;
import com.core.dagger.DaggerBottomSheetFragment;

import javax.inject.Inject;

/**
 * @author volkanhotur
 */

@SuppressWarnings({"CastCanBeRemovedNarrowingVariableType", "unchecked"})
public abstract class AbstractBottomSheetDialog<VM extends ViewModel, VDB extends ViewDataBinding> extends DaggerBottomSheetFragment implements AbstractView {

    private AlertDialog dialog;

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false);

        ViewModel viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(getViewModel());

        onInitialized(savedInstanceState, (VM) viewModel, (VDB) binding);

        return binding.getRoot();
    }

    protected abstract Class<VM> getViewModel();

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void onInitialized(Bundle savedInstanceState, VM viewModel, VDB binding);

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void showLoadingBar() {
        if (dialog == null) {
            dialog = new AlertDialog.Builder(context(), R.style.DialogStyle)
                    .setView(R.layout.view_progress)
                    .setCancelable(false)
                    .create();
        }

        if(dialog.getWindow() != null) {
            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }

        if (dialog.isShowing()) {
            return;
        }

        dialog.show();
    }

    @Override
    public void hideLoadingBar() {
        dialog.dismiss();
    }
}
