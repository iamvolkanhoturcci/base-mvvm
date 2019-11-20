package com.volkanhotur.basemvvm.android.base.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import com.core.BaseBottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.volkanhotur.basemvvm.R;
import com.volkanhotur.basemvvm.android.base.exception.UnCaughtExceptionHandler;
import com.volkanhotur.basemvvm.android.utils.IPickerSelection;

import java.util.List;
import java.util.Objects;


public abstract class DefaultPickerDialog<VM extends ViewModel, VDB extends ViewDataBinding, T> extends BaseBottomSheetDialog<VM, VDB> {

    protected IPickerSelection<T> pickerSelection;

    @StringRes
    protected int title;
    protected boolean filter;
    protected List<T> data;

    @Override
    public int getTheme() {
        return R.style.BottomSheet_Theme;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(Objects.requireNonNull(getContext()), getTheme());
    }

    @Override
    public void onStart() {
        super.onStart();
        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtExceptionHandler(getActivity()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getDialog().setOnShowListener(dialog -> {
            BottomSheetDialog d = (BottomSheetDialog) dialog;
            FrameLayout bottomSheet = d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            BottomSheetBehavior mBehavior = BottomSheetBehavior.from(Objects.requireNonNull(bottomSheet));
            mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            DisplayMetrics displayMetrics = Objects.requireNonNull(getActivity()).getResources().getDisplayMetrics();
            bottomSheet.setMinimumHeight(displayMetrics.heightPixels);
        });
    }

    @Nullable
    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void navigateView() { }

    @Override
    public void expireSession() { }

    @Override
    public boolean isLoadingBarEnabled() {
        return false;
    }

    public void showPicker(FragmentManager manager, IPickerSelection<T> selection) {
        this.pickerSelection = selection;
        if(!isAdded()) {
            showNow(manager, this.getClass().getSimpleName());
        }
    }

    public abstract DefaultPickerDialog setTitle(@StringRes int title);

    public abstract DefaultPickerDialog setData(List<T> data);

    public abstract DefaultPickerDialog filter(boolean enable);
}
