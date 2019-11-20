package com.volkanhotur.basemvvm.android.utils;

import android.app.Dialog;

import androidx.annotation.Nullable;

public interface IPickerSelection<T> {
    void onSelectedItem(@Nullable T data, Dialog dialog);
}
